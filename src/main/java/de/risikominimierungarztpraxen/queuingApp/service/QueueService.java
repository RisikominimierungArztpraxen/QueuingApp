package de.risikominimierungarztpraxen.queuingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointment;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentChange;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentCreator;

@Service
public class QueueService {

    private final OfficeService officeService;

    @Autowired
    public QueueService(OfficeService officeService) {
        this.officeService = officeService;
    }

    public void deleteAppointment(String officeId, int patientId, LocalDate day) {

    }

    public ApiAppointment getAppointment(String officeId, int patientId, LocalDate day) {
        return null;
    }

    public ApiAppointment createAppointMent(String officeId, LocalDate day, ApiAppointmentCreator appointmentCreator) {
        return null;
    }

    public ApiAppointment updateAppointment(String officeId, LocalDate day, int patientId, ApiAppointmentChange appointmentChange) {
        return null;
    }

    public void replaceQueue(String officeId, LocalDate day, List<ApiAppointmentCreator> appointments) {

    }
}
