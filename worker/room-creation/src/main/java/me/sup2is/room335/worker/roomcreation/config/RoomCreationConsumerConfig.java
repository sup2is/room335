package me.sup2is.room335.worker.roomcreation.config;

import me.sup2is.room335.kafka.config.KafkaConsumerConfig;
import me.sup2is.room335.worker.roomcreation.consumer.RoomCreationMessage;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class RoomCreationConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory roomCreationMessageKafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        return KafkaConsumerConfig.createKafkaListenerContainerFactory(kafkaProperties, RoomCreationMessage.class);
    }

}
