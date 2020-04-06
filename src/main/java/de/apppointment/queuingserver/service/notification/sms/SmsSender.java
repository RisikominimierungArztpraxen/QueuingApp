package de.apppointment.queuingserver.service.notification.sms;

public interface SmsSender {

    void sendMessage(String number, String text);
}
