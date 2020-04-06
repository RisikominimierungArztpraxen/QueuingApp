package de.apppointment.queuingserver.service.notification.pager;

import de.apppointment.queuingserver.service.notification.sms.TwilioSmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyPagerSender implements PagerSender {
    private static final Logger LOG = LoggerFactory.getLogger(DummyPagerSender.class);

    @Override
    public void sendMessage(String pagerId, String text) {
        LOG.info("Not implemented yet until we find out how the pager APIs do work...");
    }
}
