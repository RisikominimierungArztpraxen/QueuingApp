package de.apppointment.queuingserver.service.notification;

import de.apppointment.queuingserver.rest.model.NotificationDto;
import de.apppointment.queuingserver.service.notification.pager.PagerSender;
import de.apppointment.queuingserver.service.notification.sms.SmsSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.management.Notification;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceImplTest {

    @Mock
    private SmsSender smsSenderMock;

    @Mock
    private PagerSender pagerSenderMock;

    private NotificationServiceImpl service2Test;

    @Before
    public void setUp() throws Exception {
        service2Test = new NotificationServiceImpl(smsSenderMock, pagerSenderMock);
    }

    @Test
    public void testSendSms() throws Exception {
        // arrange
        NotificationDto notification = new NotificationDto();
        notification.setType(NotificationDto.TypeEnum.SMS);
        notification.setIdentifier("+4915145678905");

        // act
        service2Test.sendNotification(notification, "Testing text");

        // assert
        verify(smsSenderMock).sendMessage("+4915145678905","Testing text");
        verify(pagerSenderMock, never()).sendMessage(anyString(), anyString());
    }

    @Test
    public void testSendSmsInvalidNumber() {
        // arrange
        NotificationDto notification = new NotificationDto();
        notification.setType(NotificationDto.TypeEnum.SMS);
        notification.setIdentifier("+49123456789");

        // act
        try {
            service2Test.sendNotification(notification, "Testing text");
            fail("This should have triggered an exception as the number is invalid!");
        } catch (NotificationException e) {
            // Do nothing that should be the case!
        }
    }

    @Test
    public void testSendToPager() throws Exception {
        // arrange
        NotificationDto notification = new NotificationDto();
        notification.setType(NotificationDto.TypeEnum.BEEPER);
        notification.setIdentifier("pager-0815");

        // act
        service2Test.sendNotification(notification, "Testing text");

        // assert
        verify(pagerSenderMock).sendMessage("pager-0815","Testing text");
        verify(smsSenderMock, never()).sendMessage(anyString(), anyString());
    }

    @Test
    public void testSendToApp() {
        // arrange
        NotificationDto notification = new NotificationDto();
        notification.setType(NotificationDto.TypeEnum.APP);
        notification.setIdentifier("+49123456789");

        // act
        try {
            service2Test.sendNotification(notification, "Testing text");
            fail("This should have triggered an exception as sending to app is not implemennted yet!");
        } catch (NotificationException e) {
            // Do nothing that should be the case!
        }
    }
}