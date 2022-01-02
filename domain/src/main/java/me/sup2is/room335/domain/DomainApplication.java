package me.sup2is.room335.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomainApplication.class);
    }
}
