package de.risikominimierungarztpraxen.queuingApp.service;

import java.util.List;

import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointment;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentBase;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentCreator;
import de.risikominimierungarztpraxen.queuingApp.model.ApiNotification;

public class Appointment extends ApiAppointmentBase {

    private List<ApiNotification> notifications;
    private int estimatedWaitingTime;

    public Appointment(ApiAppointmentCreator creator, int estimatedWaitingTime) {
        super(creator.getTime(), creator.getPatientId(), creator.getEstimatedInMinutes());
        this.notifications = creator.getNotifications();
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public void setEstimatedWaitingTime(int estimatedWaitingTime) {
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public List<ApiNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<ApiNotification> notifications) {
        this.notifications = notifications;
    }

    public ApiAppointment toAppointment(int place) {
        return new ApiAppointment(getTime(), getPatientId(), getEstimatedInMinutes(), place, estimatedWaitingTime);
    }

}
