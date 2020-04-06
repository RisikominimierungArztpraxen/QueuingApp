package de.apppointment.queuingserver.service.notification.pager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagerConfig {

    @Bean
    public PagerSender pagerSender() {
        return new DummyPagerSender();
    }
}
