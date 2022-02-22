package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.api.room.dto.RoomCreationMessage;
import me.sup2is.room335.kafka.config.KafkaProducerConfig;
import me.sup2is.room335.kafka.config.KafkaTopicProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class RoomCreationEventPublisher {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishRoomCreationEvent(Long roomId, RoomCreateDto.Request request) {

        kafkaTemplate.send(kafkaTopicProperties.getRoomCreation(),
                RoomCreationMessage.builder()
                        .roomNumber(request.getRoomNumber())
                        .roomFloor(request.getRoomFloor())
                        .roomName(request.getRoomName())
                        .roomType(request.getRoomType())
                        .price(request.getPrice())
                        .roomId(roomId)
                        .build()
        );

    }
}
