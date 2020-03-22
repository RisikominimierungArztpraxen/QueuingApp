package de.risikominimierungarztpraxen.queuingApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationSchedulerService {

    private QueueService queueService;
    private static final Logger LOG = LoggerFactory.getLogger(NotificationSchedulerService.class);

    @Autowired
    public NotificationSchedulerService(QueueService queueService) {
        this.queueService = queueService;
    }

    /**
     * Update queues every minute
     */
    @Scheduled(cron = "0 * * ? * * *")
    public void removeOneMinuteFromAllAppointments() {
        LOG.info("running scheduler");
        this.queueService.removeOneMinuteFromAllAppointments();
    }
}
