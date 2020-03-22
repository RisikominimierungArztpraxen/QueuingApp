package de.risikominimierungarztpraxen.queuingApp.service.queue;

import java.util.List;

import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentDto;
import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentBaseDto;
import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentCreatorDto;
import de.risikominimierungarztpraxen.queuingApp.rest.model.NotificationDto;

public class Appointment extends AppointmentBaseDto {

    private List<NotificationDto> notifications;
    private int estimatedWaitingTime;

    public Appointment(AppointmentCreatorDto creator, int estimatedWaitingTime) {
        super(creator.getTime(), creator.getPatientId(), creator.getEstimatedInMinutes());
        this.notifications = creator.getNotifications();
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public void setEstimatedWaitingTime(int estimatedWaitingTime) {
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public int getEstimatedWaitingTime() {
        return estimatedWaitingTime;
    }

    public List<NotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationDto> notifications) {
        this.notifications = notifications;
    }

    public AppointmentDto toAppointment(int place) {
        return new AppointmentDto(getTime(), getPatientId(), getEstimatedInMinutes(), place, estimatedWaitingTime);
    }

}
