package me.sup2is.room335.worker.roomcreation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = "me.sup2is.room335")
public class RoomCreationWorkerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(RoomCreationWorkerApplication.class);
    }
}
