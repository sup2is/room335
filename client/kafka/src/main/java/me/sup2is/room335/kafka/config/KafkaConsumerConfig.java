package me.sup2is.room335.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sup2is.room335.core.ObjectMapperFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.BatchLoggingErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    public static <T> ConcurrentKafkaListenerContainerFactory<String, T> createKafkaListenerContainerFactory(KafkaProperties kafkaProperties,
                                                                                                             Class<T> clazz){
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(creteConsumerFactory(kafkaProperties, clazz));

        //https://docs.spring.io/spring-kafka/reference/html/#committing-offsets
        factory.getContainerProperties().setAckMode(kafkaProperties.getListener().getAckMode());

//        factory.setBatchListener(true);
//        factory.setBatchErrorHandler(new BatchLoggingErrorHandler());
        return factory;
    }

    private static <T> ConsumerFactory<String, T> creteConsumerFactory(KafkaProperties kafkaProperties,
                                                                       Class<T> clazz) {

        JsonDeserializer<T> jsonDeserializer = new JsonDeserializer<>(clazz, ObjectMapperFactory.newDefault(), false){
            @Override
            public T deserialize(String topic, Headers headers, byte[] data) {
                try {
                    T result = super.deserialize(topic, headers, data);
                    log.info("KafkaConsumer Deserializer : {}, {}", topic, result);

                    return result;
                } catch (Exception e) {
                    log.error("Failed to KafkaConsumer Deserializer");
                    return null;
                }
            }
        };

        var props = kafkaProperties.buildConsumerProperties();

        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                jsonDeserializer);
    }


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
