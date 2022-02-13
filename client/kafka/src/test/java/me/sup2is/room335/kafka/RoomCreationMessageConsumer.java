package me.sup2is.room335.kafka;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Getter
@Component
public class RoomCreationMessageConsumer {

    private CountDownLatch latch = new CountDownLatch(1);
    private Object payload = null;

    @KafkaListener(
            topics = "${topic.room-creation}",
            groupId = "room-creation"
    )
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        log.info("received payload='{}'", consumerRecord.toString());
        payload = consumerRecord.value();
        latch.countDown();
    }
}
