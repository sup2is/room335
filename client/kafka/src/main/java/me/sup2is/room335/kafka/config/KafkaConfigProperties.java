package me.sup2is.room335.kafka.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@ConfigurationProperties(prefix = "spring.kafka")
@AllArgsConstructor
@ConstructorBinding
public class KafkaConfigProperties {
    
    private final List<String> bootstrapServers;

}