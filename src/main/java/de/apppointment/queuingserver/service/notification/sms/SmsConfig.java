package de.apppointment.queuingserver.service.notification.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

    @Value("${twilio-account-sid:#{null}}")
    private String accountSid;

    @Value("${twilio-auth-token:#{null}}")
    private String authToken;

    @Value("${twilio-sender-name:#{null}}")
    private String senderName;

    @Bean
    public SmsSender smsSender() {
        // Here we might create another sender depending on the SMS-Gateway we will finally use:
        return new TwilioSmsSender(accountSid, authToken, senderName);
    }
}