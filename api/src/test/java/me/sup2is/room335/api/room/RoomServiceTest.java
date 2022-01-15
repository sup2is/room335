package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractServiceTestConfig;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.domain.room.RoomType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@RequiredArgsConstructor
class RoomServiceTest extends AbstractServiceTestConfig {

    final RoomService roomService;

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