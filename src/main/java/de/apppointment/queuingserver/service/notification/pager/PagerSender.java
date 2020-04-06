package de.apppointment.queuingserver.service.notification.pager;

public interface PagerSender {

    void sendMessage(String pagerId, String text);
}
