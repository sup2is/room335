package me.sup2is.room335.kafka;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.TestConstructor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Import(TestConsumer.class)
class KafkaPubSubTest {

    final KafkaTemplate<String, String> kafkaTemplate;
    final TestConsumer consumer;

    @Value(value = "${kafka.testTopicName}")
    String testTopicName;

    @Test
    void 이벤트_발행_소비() throws InterruptedException {
        //given
        String message = "test-message-" + System.currentTimeMillis();

        //when
        kafkaTemplate.send(testTopicName, message);

        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);

        //then
        assertThat(consumer.getLatch().getCount()).isEqualTo(0L);
        assertThat(consumer.getPayload()).contains(message);
    }

}
