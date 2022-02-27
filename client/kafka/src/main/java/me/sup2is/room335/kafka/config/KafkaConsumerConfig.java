package me.sup2is.room335.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sup2is.room335.core.ObjectMapperFactory;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    public static <T> ConcurrentKafkaListenerContainerFactory<String, T> createKafkaListenerContainerFactory(final KafkaProperties kafkaProperties,
                                                                                                             final Class<T> clazz) {
        final ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(creteConsumerFactory(kafkaProperties, clazz));

        //https://docs.spring.io/spring-kafka/reference/html/#committing-offsets
        factory.getContainerProperties().setAckMode(kafkaProperties.getListener().getAckMode());

//        factory.setBatchListener(true);
//        factory.setBatchErrorHandler(new BatchLoggingErrorHandler());
        return factory;
    }

    private static <T> ConsumerFactory<String, T> creteConsumerFactory(final KafkaProperties kafkaProperties,
                                                                       final Class<T> clazz) {

        final JsonDeserializer<T> jsonDeserializer = new JsonDeserializer<>(clazz, ObjectMapperFactory.newDefault(), false) {
            @Override
            public T deserialize(final String topic, final Headers headers, final byte[] data) {
                try {
                    final T result = super.deserialize(topic, headers, data);
                    log.info("KafkaConsumer Deserializer : {}, {}", topic, result);

                    return result;
                } catch (final Exception e) {
                    log.error("Failed to KafkaConsumer Deserializer");
                    return null;
                }
            }
        };

        final var props = kafkaProperties.buildConsumerProperties();

        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                jsonDeserializer);
    }

}
