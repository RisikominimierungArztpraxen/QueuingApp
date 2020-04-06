package de.apppointment.queuingserver.service.queue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import de.apppointment.queuingserver.persistence.entities.AppointmentDao;
import de.apppointment.queuingserver.persistence.entities.NotificationDao;
import de.apppointment.queuingserver.persistence.entities.ReceiverType;
import de.apppointment.queuingserver.persistence.repository.AppointmentRepository;
import de.apppointment.queuingserver.persistence.repository.DoctorsOfficeRepository;
import de.apppointment.queuingserver.rest.model.NotificationDto;
import de.apppointment.queuingserver.service.notification.NotificationService;
import de.apppointment.queuingserver.service.office.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.apppointment.queuingserver.rest.model.AppointmentDto;
import de.apppointment.queuingserver.rest.model.AppointmentChangeDto;
import de.apppointment.queuingserver.rest.model.AppointmentCreatorDto;

import javax.transaction.Transactional;

@Service
@Transactional
public class QueueService {

    private static final Logger LOG = LoggerFactory.getLogger(QueueService.class);
    private final OfficeService officeService;
    private final AppointmentRepository appointmentRepository;
    private final DoctorsOfficeRepository officeRepository;
    private final NotificationService notificationService;
    private final Map<String, Map<LocalDate, Queue>> queues = new HashMap<>();


    @Autowired
    public QueueService(OfficeService officeService, AppointmentRepository appointmentRepository, DoctorsOfficeRepository officeRepository, NotificationService notificationService) {
        this.officeService = officeService;
        this.appointmentRepository = appointmentRepository;
        this.officeRepository = officeRepository;
        this.notificationService = notificationService;
        // TODO: reload queues from DB to restore state after restart. When 'real' development starts this can be removed again as we will always have a DB-backed queue in a cluster-scenario!
        appointmentRepository.findAllByOrderByTime().spliterator().forEachRemaining(appointmentDao -> restoreToQueue(appointmentDao));
    }

    public void deleteAppointment(String officeId, String patientId, LocalDate day) {
        checkOfficeId(officeId);
        findQueue(officeId, day).ifPresent(queue -> {
            queue.removeAppointment(patientId);
        });
    }

    public AppointmentDto getAppointment(String officeId, String patientId, LocalDate day) {
        checkOfficeId(officeId);
        Queue queue = findQueue(officeId, day)
                .orElseThrow(() -> new IllegalArgumentException("There was no queue for the given day or office found!"));
        return queue.findAppointment(patientId).orElseThrow(() -> new IllegalArgumentException("No appointment for given user found!"));
    }

    public AppointmentDto createAppointment(String officeId, LocalDate day, AppointmentCreatorDto appointmentCreator) {
        checkOfficeId(officeId);
        Queue queue = findOrCreateQueue(officeId, day);
        AppointmentDto appointment = queue.addAppointment(appointmentCreator);
        AppointmentDao appointmentDao = mapToDao(officeId, day, appointmentCreator);
        appointmentRepository.save(appointmentDao);
        return appointment;
    }

    public void removeOneMinuteFromAllAppointments() {
        this.queues.forEach((officeId, officeQueue) -> {
            officeQueue.forEach((day, queue) -> {
                queue.removeOneMinuteFromAllAppointments();
                this.refreshQueueEstimationInDb(queue);
            });
        });
    }

    private void refreshQueueEstimationInDb(Queue queue) {
        // Do we really need this at the moment?
        // At the moment DB is only for persistence on server crashes.
        // Might be necessary later when using clustering for bette scalability.
    }

    public AppointmentDto updateAppointment(String officeId, LocalDate day, String patientId, AppointmentChangeDto appointmentChange) {
        checkOfficeId(officeId);
        Queue queue = findQueue(officeId, day)
                .orElseThrow(() -> new IllegalArgumentException("There was no queue for the given day or office found!"));
        return queue.updateAppointment(patientId, appointmentChange);
    }

    public void replaceQueue(String officeId, LocalDate day, List<AppointmentCreatorDto> appointments) {
        checkOfficeId(officeId);
        Queue queue = findOrCreateQueue(officeId, day);
        queue.clear();
        appointments.forEach(appointment -> {
            queue.addAppointment(appointment);
        });
    }

    private Optional<Queue> findQueue(String officeId, LocalDate day) {
        Map<LocalDate, Queue> officeQueues = this.queues.get(officeId);
        if (officeQueues == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(officeQueues.get(day));
    }

    private Queue findOrCreateQueue(String officeId, LocalDate day) {
        Map<LocalDate, Queue> officeQueues = queues.get(officeId);
        if (officeQueues == null) {
            officeQueues = new HashMap<>();
            queues.put(officeId, officeQueues);
        }
        Queue queue = officeQueues.get(day);
        if (queue == null) {
            queue = new Queue(notificationService);
            officeQueues.put(day, queue);
        }
        return queue;
    }

    private void checkOfficeId(String officeId) {
        if (officeService.findOffice(officeId) == null) {
            throw new IllegalArgumentException("officeId " + officeId + " not found. Please create the office first.");
        }
    }

    private AppointmentDao mapToDao(String officeId, LocalDate day, AppointmentCreatorDto appointmentCreator) {
        AppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.setOfficeDao(officeRepository.findByOfficeId(officeId));
        appointmentDao.setEstimatedInMinutes(appointmentCreator.getEstimatedInMinutes());
        appointmentDao.setPatientId(appointmentCreator.getPatientId());
        String appointmentTimeString = appointmentCreator.getTime();
        String[] appointmentTimeparts = appointmentTimeString.split(":");
        int hours = Integer.parseInt(appointmentTimeparts[0]);
        int minutes = Integer.parseInt(appointmentTimeparts[1]);
        LocalDateTime time = LocalDateTime.of(day.getYear(), day.getMonth(), day.getDayOfMonth(), hours, minutes);
        appointmentDao.setTime(time);
        List<NotificationDto> notificationDtos = appointmentCreator.getNotifications();
        Set<NotificationDao> receivers = notificationDtos.stream().map(n -> mapToDao(n)).collect(Collectors.toSet());
        appointmentDao.setNotificationDaos(receivers);
        return appointmentDao;
    }

    private NotificationDao mapToDao(NotificationDto notification) {
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setAddress(notification.getIdentifier());
        notificationDao.setReceiverType(getReceiverType(notification.getType()));
        return notificationDao;
    }

    private NotificationDto mapToDto(NotificationDao notification) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdentifier(notification.getAddress());
        notificationDto.setType(getReceiverType(notification.getReceiverType()));
        return notificationDto;
    }

    private ReceiverType getReceiverType(NotificationDto.TypeEnum type) {
        switch (type) {
            case APP:
                return ReceiverType.APP;
            case SMS:
                return ReceiverType.SMS;
            case BEEPER:
                return ReceiverType.BEEPER;
            default:
                LOG.warn("Unkown Receiver type!");
                return null;
        }
    }

    private NotificationDto.TypeEnum getReceiverType(ReceiverType type) {
        switch (type) {
            case APP:
                return NotificationDto.TypeEnum.APP;
            case SMS:
                return NotificationDto.TypeEnum.SMS;
            case BEEPER:
                return NotificationDto.TypeEnum.BEEPER;
            default:
                LOG.warn("Unkown Receiver type!");
                return null;
        }
    }

    private void restoreToQueue(AppointmentDao appointmentDao) {
        LocalDateTime appointmentTime = appointmentDao.getTime();
        LocalDate day = LocalDate.from(appointmentDao.getTime());
        Queue queue = findOrCreateQueue(appointmentDao.getOfficeDao().getOfficeId(), day);
        String time = appointmentTime.getHour() + ":" + appointmentTime.getMinute();
        List<NotificationDto> notifications = appointmentDao.getNotificationDaos().stream().map(notificationDao -> mapToDto(notificationDao)).collect(Collectors.toList());
        AppointmentCreatorDto appointment = new AppointmentCreatorDto(time, appointmentDao.getPatientId(), appointmentDao.getEstimatedInMinutes(), notifications);
        queue.addAppointment(appointment);
    }
}
