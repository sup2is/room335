package me.sup2is.room335.api.room;

import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.domain.model.Money;
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
class RoomCommandServiceTest {

    @InjectMocks
    RoomCommandService roomCommandService;

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
        this.roomCommandService.createRoom(request);

        //then
        then(this.roomRepository).should().save(any());
        then(this.roomValidator).should().checkUniqueRoom(any());
        then(this.roomCreationEventPublisher).should().publishRoomCreationEvent(null, request);
    }

}