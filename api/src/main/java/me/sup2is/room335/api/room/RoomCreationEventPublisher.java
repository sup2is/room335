package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.KafkaTopicProperties;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.api.room.dto.RoomCreationMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomCreationEventPublisher {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishRoomCreationEvent(final Long roomId, final RoomCreateDto.Request request) {

        this.kafkaTemplate.send(this.kafkaTopicProperties.getRoomCreation(),
                RoomCreationMessage.builder()
                        .roomNumber(request.getRoomNumber())
                        .roomFloor(request.getRoomFloor())
                        .roomName(request.getRoomName())
                        .roomType(request.getRoomType())
                        .description(request.getDescription())
                        .price(request.getPrice())
                        .roomId(roomId)
                        .build()
        );

    }
}
