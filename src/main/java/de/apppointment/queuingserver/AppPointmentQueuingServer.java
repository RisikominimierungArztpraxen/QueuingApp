package de.apppointment.queuingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@ComponentScan(basePackages = {"de.apppointment.queuingserver", "de.apppointment.queuingserver.rest.controller"})
public class AppPointmentQueuingServer {

    public static void main(String[] args) throws Exception {
        new SpringApplication(AppPointmentQueuingServer.class).run(args);
    }
}
