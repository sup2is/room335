package me.sup2is.room335.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = "me.sup2is.room335")
public class KafkaApplication {

    public static void main(final String[] args) {
        SpringApplication.run(KafkaApplication.class);
    }
}
