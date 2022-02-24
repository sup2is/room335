package me.sup2is.room335.worker.roomcreation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ConfigurationPropertiesScan
@SpringBootApplication
public class RoomCreationWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomCreationWorkerApplication.class);
    }
}
