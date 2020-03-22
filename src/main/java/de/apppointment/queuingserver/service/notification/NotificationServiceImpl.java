package de.apppointment.queuingserver.service.notification;

import de.apppointment.queuingserver.rest.model.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void sendNotification(NotificationDto notification, String text) {
        //TODO: For development only, must be removed or anonymized when implemented:
        LOG.debug("Sending message " + text + " to " + notification.getIdentifier());
    }
}
