package de.risikominimierungarztpraxen.queuingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import de.risikominimierungarztpraxen.queuingApp.model.Appointment;
import de.risikominimierungarztpraxen.queuingApp.model.AppointmentChange;
import de.risikominimierungarztpraxen.queuingApp.model.AppointmentCreator;

@Service
public class QueueService {

    private final OfficeService officeService;

    @Autowired
    public QueueService(OfficeService officeService) {
        this.officeService = officeService;
    }

    public void deleteAppointment(String officeId, int patientId, LocalDate day) {

    }

    public Appointment getAppointment(String officeId, int patientId, LocalDate day) {
        return null;
    }

    public Appointment createAppointMent(String officeId, LocalDate day, AppointmentCreator appointmentCreator) {
        return null;
    }

    public Appointment updateAppointment(String officeId, LocalDate day, int patientId, AppointmentChange appointmentChange) {
        return null;
    }

    public void replaceQueue(String officeId, LocalDate day, List<AppointmentCreator> appointments) {

    }
}
