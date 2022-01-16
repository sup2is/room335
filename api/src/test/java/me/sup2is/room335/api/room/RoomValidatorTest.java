package me.sup2is.room335.api.room;

import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomRepository;
import me.sup2is.room335.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RoomValidatorTest {

    @InjectMocks
    RoomValidator roomValidator;

    @Mock
    RoomRepository roomRepository;

    @Test
    void 같은_room_number가_있을_경우_IllegalArgumentException() {
        //given
        String newRoomNumber = "newRoomNumber";
        Room room = Room.builder()
                .roomNumber(newRoomNumber)
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomName("")
                .roomFloor(1)
                .build();

        given(roomRepository.findByRoomNumber(newRoomNumber))
                .willReturn(Optional.of(Room.builder().build()));

        //when
        //then
        assertThrows(IllegalArgumentException.class,
                () -> roomValidator.checkUniqueRoom(room)
        );
    }

}