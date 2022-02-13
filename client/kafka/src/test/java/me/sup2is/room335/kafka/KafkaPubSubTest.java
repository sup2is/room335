package me.sup2is.room335.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.sup2is.room335.core.ObjectMapperFactory;
import me.sup2is.room335.kafka.config.KafkaTopicProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.TestConstructor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Import({StringMessageConsumer.class, TestMessageConsumer.class})
class KafkaPubSubTest {

    final KafkaTemplate<String, Object> kafkaTemplate;
    final StringMessageConsumer stringMessageConsumer;
    final TestMessageConsumer testMessageConsumer;
    final KafkaTopicProperties kafkaTopicProperties;
    final ObjectMapper objectMapper = ObjectMapperFactory.newDefault();

    @Test
    void 문자열_이벤트_발행_소비() throws InterruptedException {
        //given
        String message = "test-message-" + System.currentTimeMillis();

        //when
        kafkaTemplate.send(kafkaTopicProperties.getTestTopicName(), message);

        stringMessageConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);

        //then
        assertThat(stringMessageConsumer.getLatch().getCount()).isEqualTo(0L);
        assertThat(stringMessageConsumer.getPayload().toString()).contains(message);
    }

    @Test
    void 객체_이벤트_발행_소비() throws InterruptedException, IOException {
        //given
        TestMessage sampleMessage = TestMessage.createSampleMessage();

        //when
        kafkaTemplate.send(kafkaTopicProperties.getRoomCreation(), sampleMessage);

        testMessageConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);

        //then
        assertThat(testMessageConsumer.getLatch().getCount()).isEqualTo(0L);
        TestMessage receivedMessage = objectMapper.readValue(testMessageConsumer.getPayload().toString(), TestMessage.class);
        assertThat(receivedMessage).isEqualTo(sampleMessage);
    }

}
