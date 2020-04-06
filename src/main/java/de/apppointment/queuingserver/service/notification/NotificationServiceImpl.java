package de.apppointment.queuingserver.service.notification;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import de.apppointment.queuingserver.rest.model.NotificationDto;
import de.apppointment.queuingserver.service.notification.pager.PagerSender;
import de.apppointment.queuingserver.service.notification.sms.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final SmsSender smsSender;
    private final PagerSender pagerSender;

    public NotificationServiceImpl(SmsSender smsSender, PagerSender pagerSender) {
        this.smsSender = smsSender;
        this.pagerSender = pagerSender;
    }

    @Override
    public void sendNotification(NotificationDto notification, String text) throws NotificationException{
        NotificationDto.TypeEnum notificationTpe = notification.getType();
        switch (notificationTpe) {
            case SMS:
                sendSMS(notification.getIdentifier(), text);
                break;
            case BEEPER:
                sendToPager(notification.getIdentifier(), text);
                break;
            case APP:
                sendToApp(notification.getIdentifier(), text);
                break;
            default:
                throw new NotificationException("Unkown NotificationType " + notificationTpe);
        }
    }

    private void sendSMS(String phonenumber, String text) throws NotificationException{
        if (!isValidNumber(phonenumber)) {
            throw new NotificationException("Could not send notification to '" + phonenumber + "' as it seems to be no valid phonenumber");
        }

        smsSender.sendMessage(phonenumber, text);
    }

    private void sendToPager(String pagerId, String text) {
        pagerSender.sendMessage(pagerId, text);
    }

    private void sendToApp(String phonenumber, String text) throws NotificationException {
        throw new NotificationException("Not implemented yet until push notifications get part of the project");
    }

    private boolean isValidNumber(String number) {
        boolean result = false;
        final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        number = number.trim();

        if (number.startsWith("00")) {
            // Replace 00 at beginning with +
            number = "+" + number.substring(2);
        }

        try {
            final Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(number, "DE");
            result = phoneNumberUtil.getNumberType(phoneNumber).equals(PhoneNumberUtil.PhoneNumberType.MOBILE);
        } catch (NumberParseException ex) {
            LOG.warn("Could not parse number: " + number, ex);
            result = false;
        }

        return result;
    }
}
