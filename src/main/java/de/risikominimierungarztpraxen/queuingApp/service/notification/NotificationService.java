package de.risikominimierungarztpraxen.queuingApp.service.notification;

import de.risikominimierungarztpraxen.queuingApp.rest.model.NotificationDto;

public interface NotificationService {
    void sendNotification(NotificationDto notification, String text);
}
