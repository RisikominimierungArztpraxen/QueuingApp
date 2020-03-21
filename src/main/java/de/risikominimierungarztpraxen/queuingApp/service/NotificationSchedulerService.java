package de.risikominimierungarztpraxen.queuingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationSchedulerService {

    private QueueService queueService;

    @Autowired
    public NotificationSchedulerService(QueueService queueService) {
        this.queueService = queueService;
    }

    /**
     * Update queues every minute
     */
    @Scheduled(cron = "0 * * ? * * *")
    public void removeOneMinuteFromAllAppointments() {
        this.queueService.removeOneMinuteFromAllAppointments();
    }
}
