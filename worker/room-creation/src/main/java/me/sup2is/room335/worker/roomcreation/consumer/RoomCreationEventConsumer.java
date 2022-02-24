package me.sup2is.room335.worker.roomcreation.consumer;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.worker.roomcreation.handler.RoomCreationRedisHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomCreationEventConsumer {

    final RoomCreationRedisHandler roomCreationRedisHandler;

    @KafkaListener(topics = "${topic.room-creation}",
            groupId = "room-creation",
            containerFactory = "roomCreationMessageKafkaListenerContainerFactory")
    public void onMessage(List<RoomCreationMessage> roomCreationMessages, Acknowledgment acknowledgment) {

        roomCreationRedisHandler.handle(roomCreationMessages);

        acknowledgment.acknowledge();

    }

}
