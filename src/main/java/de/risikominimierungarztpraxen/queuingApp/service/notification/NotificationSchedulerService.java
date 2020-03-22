package de.risikominimierungarztpraxen.queuingApp.service.notification;

import de.risikominimierungarztpraxen.queuingApp.service.queue.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationSchedulerService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationSchedulerService.class);
    private static final int MINUTE = 60 * 1000;
    private QueueService queueService;

    @Autowired
    public NotificationSchedulerService(QueueService queueService) {
        this.queueService = queueService;
    }

    /**
     * Update queues every minute
     */
    @Scheduled(fixedRate = MINUTE)
    public void removeOneMinuteFromAllAppointments() {
        LOG.info("running scheduler");
        this.queueService.removeOneMinuteFromAllAppointments();
    }
}
