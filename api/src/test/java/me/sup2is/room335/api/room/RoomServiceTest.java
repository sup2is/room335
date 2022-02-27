package me.sup2is.room335.api.room;

import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.api.room.dto.RoomDto;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomRepository;
import me.sup2is.room335.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    RoomService roomService;

    @Mock
    RoomRepository roomRepository;

    @Mock
    RoomValidator roomValidator;

    @Mock
    RoomCreationEventPublisher roomCreationEventPublisher;

    @Test
    void 객실_생성() {
        //given
        final RoomCreateDto.Request request = RoomCreateDto.Request.builder()
                .roomFloor(1)
                .roomName("roomName")
                .roomNumber("roomNumber")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .price(Money.wons(1000))
                .build();

        //when
        this.roomService.createRoom(request);

        //then
        then(this.roomRepository).should().save(any());
        then(this.roomValidator).should().checkUniqueRoom(any());
        then(this.roomCreationEventPublisher).should().publishRoomCreationEvent(null, request);
    }

    @Test
    void 객실_조회() {
        //given
        final long roomId = 1L;

        final Room room = Room.builder()
                .roomImages(List.of())
                .description("description")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomName("roomName")
                .roomFloor(1)
                .roomNumber("roomNumber")
                .build();

        given(this.roomRepository.findById(roomId))
                .willReturn(Optional.of(room));

        //when
        final RoomDto.Response response = this.roomService.getRoom(roomId);

        //then
        assertThat(response.getRoomImages()).isEqualTo(room.getRoomImages());
        assertThat(response.getRoomFloor()).isEqualTo(room.getRoomFloor());
        assertThat(response.getRoomNumber()).isEqualTo(room.getRoomNumber());
        assertThat(response.getRoomName()).isEqualTo(room.getRoomName());
        assertThat(response.getDescription()).isEqualTo(room.getDescription());
        assertThat(response.getRoomType()).isEqualTo(room.getRoomType());
    }

}