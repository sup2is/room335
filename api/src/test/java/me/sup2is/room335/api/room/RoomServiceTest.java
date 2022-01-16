package me.sup2is.room335.api.room;

import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.domain.room.RoomRepository;
import me.sup2is.room335.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    RoomService roomService;

    @Mock
    RoomRepository roomRepository;

    @Test
    void 객실_생성() {
        //given
        RoomCreateDto.Request build = RoomCreateDto.Request.builder()
                .roomFloor(1)
                .roomName("roomName")
                .roomNumber("roomNumber")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .build();

        //when
        roomService.createRoom(build);

        //then
        then(roomRepository).should().save(any());
    }

}