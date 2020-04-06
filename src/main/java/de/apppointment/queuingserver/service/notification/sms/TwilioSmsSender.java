package de.apppointment.queuingserver.service.notification.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwilioSmsSender implements SmsSender {
    private static final Logger LOG = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final String senderName;

    TwilioSmsSender(String accountSid, String authToken, String senderName) {
        this.senderName = senderName;
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendMessage(String number, String text) {
        Message message = Message.creator(new PhoneNumber(number),
                new PhoneNumber(senderName),
                text).create();

        LOG.debug("Successfully sent message with SID " + message.getSid());
    }
}
