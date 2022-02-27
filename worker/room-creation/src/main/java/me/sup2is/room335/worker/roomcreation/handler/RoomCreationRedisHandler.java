package me.sup2is.room335.worker.roomcreation.handler;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.redis.domain.room.RoomRedisRepository;
import me.sup2is.room335.worker.roomcreation.consumer.RoomCreationMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomCreationRedisHandler {

    private final RoomRedisRepository roomRedisRepository;

    public void handle(final List<RoomCreationMessage> roomCreationMessages) {

        roomCreationMessages.forEach(roomCreationMessage
                -> roomRedisRepository.save(roomCreationMessage.toCache()));

    }
}
