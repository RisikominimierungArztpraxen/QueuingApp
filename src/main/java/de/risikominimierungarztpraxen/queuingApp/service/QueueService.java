package de.risikominimierungarztpraxen.queuingApp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointment;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentChange;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentCreator;

@Service
public class QueueService {

    private final OfficeService officeService;
    private final Map<String, Map<LocalDate, List<Appointment>>> queues = new HashMap<>();

    @Autowired
    public QueueService(OfficeService officeService) {
        this.officeService = officeService;
    }

    public void deleteAppointment(String officeId, String patientId, LocalDate day) {
        checkOfficeId(officeId);
        findQueue(officeId, day).ifPresent(queue -> {
            queue.removeIf(appointment -> appointment.getPatientId().equals(patientId));
        });
    }

    public ApiAppointment getAppointment(String officeId, String patientId, LocalDate day) {
        checkOfficeId(officeId);
        List<Appointment> queue = findQueue(officeId, day)
                .orElseThrow(() -> new IllegalArgumentException("There was no queue for the given day or office found!"));
        int place = 0;
        for (Appointment appointment : queue) {
            if (appointment.getPatientId().equals(patientId)) {
                return appointment.toAppointment(place);
            }
            place++;
        }
        throw new IllegalArgumentException("No appointment for given user found!");
    }

    public ApiAppointment createAppointMent(String officeId, LocalDate day, ApiAppointmentCreator appointmentCreator) {
        checkOfficeId(officeId);
        return null;
    }

    public ApiAppointment updateAppointment(String officeId, LocalDate day, String patientId, ApiAppointmentChange appointmentChange) {
        checkOfficeId(officeId);
        return null;
    }

    public void replaceQueue(String officeId, LocalDate day, List<ApiAppointmentCreator> appointments) {
        checkOfficeId(officeId);

    }

    private Optional<List<Appointment>> findQueue(String officeId, LocalDate day) {
        Map<LocalDate, List<Appointment>> officeQueues = this.queues.get(officeId);
        if (queues == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(officeQueues.get(day));
    }

    private void checkOfficeId(String officeId) {
        if (!this.officeService.findOffice(officeId).isPresent()) {
            throw new IllegalArgumentException("officeId " + officeId + " not found. Please create the office first.");
        }
    }
}
