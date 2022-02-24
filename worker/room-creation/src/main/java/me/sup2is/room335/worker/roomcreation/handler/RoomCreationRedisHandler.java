package me.sup2is.room335.worker.roomcreation.handler;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.worker.roomcreation.consumer.RoomCreationMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomCreationRedisHandler {

    public void handle(final List<RoomCreationMessage> roomCreationMessages) {
        //todo
    }
}
