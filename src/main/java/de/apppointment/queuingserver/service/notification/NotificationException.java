package de.apppointment.queuingserver.service.notification;

import de.apppointment.queuingserver.rest.model.NotificationDto;

public class NotificationException extends Exception {

    public NotificationException(String message) {
        super(message);
    }
}
