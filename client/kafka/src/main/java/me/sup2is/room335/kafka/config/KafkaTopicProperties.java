package me.sup2is.room335.kafka.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "topic")
@AllArgsConstructor
@ConstructorBinding
public class KafkaTopicProperties {

    private final String testStringTopic;
    private final String testMessageTopic;
    private final String roomCreation;
}
