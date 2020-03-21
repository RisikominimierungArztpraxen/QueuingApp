package de.risikominimierungarztpraxen.queuingApp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointment;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentChange;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentCreator;
import de.risikominimierungarztpraxen.queuingApp.persistence.entities.AppointmentEntity;
import de.risikominimierungarztpraxen.queuingApp.persistence.repository.AppointmentRepository;
import de.risikominimierungarztpraxen.queuingApp.persistence.repository.DoctorsOfficeRepository;

@Service
public class QueueService {

    private final OfficeService officeService;
    private final AppointmentRepository appointmentRepository;
    private final DoctorsOfficeRepository officeRepository;
    private final Map<String, Map<LocalDate, Queue>> queues = new HashMap<>();

    @Autowired
    public QueueService(OfficeService officeService, AppointmentRepository appointmentRepository, DoctorsOfficeRepository officeRepository) {
        this.officeService = officeService;
        this.appointmentRepository = appointmentRepository;
        this.officeRepository = officeRepository;
    }

    public void deleteAppointment(String officeId, String patientId, LocalDate day) {
        checkOfficeId(officeId);
        findQueue(officeId, day).ifPresent(queue -> {
            queue.removeAppointment(patientId);
        });
    }

    public ApiAppointment getAppointment(String officeId, String patientId, LocalDate day) {
        checkOfficeId(officeId);
        Queue queue = findQueue(officeId, day)
                .orElseThrow(() -> new IllegalArgumentException("There was no queue for the given day or office found!"));
        return queue.findAppointment(patientId).orElseThrow(() -> new IllegalArgumentException("No appointment for given user found!"));
    }

    public ApiAppointment createAppointMent(String officeId, LocalDate day, ApiAppointmentCreator appointmentCreator) {
        checkOfficeId(officeId);
        Queue queue = findOrCreateQueue(officeId, day);
        ApiAppointment appointment = queue.addAppointment(appointmentCreator);
        AppointmentEntity appointmentEntity = mapToDBEntity(officeId, day, appointmentCreator);
        appointmentRepository.save(appointmentEntity);
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

    }

    public ApiAppointment updateAppointment(String officeId, LocalDate day, String patientId, ApiAppointmentChange appointmentChange) {
        checkOfficeId(officeId);
        Queue queue = findQueue(officeId, day)
                .orElseThrow(() -> new IllegalArgumentException("There was no queue for the given day or office found!"));
        return queue.updateAppointment(patientId, appointmentChange);
    }

    public void replaceQueue(String officeId, LocalDate day, List<ApiAppointmentCreator> appointments) {
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
            queue = new Queue();
            officeQueues.put(day, queue);
        }
        return queue;
    }

    private void checkOfficeId(String officeId) {
        if (officeService.findOffice(officeId) == null) {
            throw new IllegalArgumentException("officeId " + officeId + " not found. Please create the office first.");
        }
    }

    private AppointmentEntity mapToDBEntity(String officeId, LocalDate day, ApiAppointmentCreator appointmentCreator) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setDoctorsOffice(officeRepository.findByOfficeId(officeId));
        appointmentEntity.setEstimatedInMinutes(appointmentCreator.getEstimatedInMinutes());
        appointmentEntity.setPatientId(appointmentCreator.getPatientId());
        String appointmentTimeString = appointmentCreator.getTime();
        String[] appointmentTimeparts = appointmentTimeString.split(":");
        int hours = Integer.parseInt(appointmentTimeparts[0]);
        int minutes = Integer.parseInt(appointmentTimeparts[1]);
        LocalDateTime time = LocalDateTime.of(day.getYear(), day.getMonth(), day.getDayOfMonth(), hours, minutes);
        appointmentEntity.setTime(time);
        return appointmentEntity;
    }
}
