package me.sup2is.room335.domain.room;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class RoomRepositoryTest {

    final RoomRepository roomRepository;

    @Test
    void 객실_생성() {
        //given
        RoomImage roomImage1 = RoomImage.builder()
                .roomImageUrl("url1")
                .isMain(true)
                .build();

        RoomImage roomImage2 = RoomImage.builder()
                .roomImageUrl("url2")
                .isMain(false)
                .build();

        Room room = Room.builder()
                .roomFloor(1)
                .roomName("")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomNumber("101")
                .build();

        room.addRoomImages(roomImage1);
        room.addRoomImages(roomImage2);

        //when
        roomRepository.save(room);

        //then
        Room findRoom = roomRepository.findById(room.getId()).get();
        assertThat(room).isEqualTo(findRoom);
    }

    @Test
    void roomNumber로_객실_조회() {
        //given
        Room room = Room.builder()
                .roomNumber("roomNumber")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomName("roomName")
                .roomFloor(1)
                .build();

        roomRepository.save(room);

        //when
        Room findRoom = roomRepository.findByRoomNumber(room.getRoomNumber()).get();

        //then
        assertThat(room).isEqualTo(findRoom);
    }

}