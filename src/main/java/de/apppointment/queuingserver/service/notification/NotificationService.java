package de.apppointment.queuingserver.service.notification;

import de.apppointment.queuingserver.rest.model.NotificationDto;

public interface NotificationService {
    void sendNotification(NotificationDto notification, String text) throws NotificationException;
}
