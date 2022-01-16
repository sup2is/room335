package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomValidator {

    private final RoomRepository roomRepository;

    public void checkUniqueRoom(Room newer) {
        roomRepository.findByRoomNumber(newer.getRoomNumber())
                .ifPresent(ignore -> {
                    throw new IllegalArgumentException();
                });
    }

}