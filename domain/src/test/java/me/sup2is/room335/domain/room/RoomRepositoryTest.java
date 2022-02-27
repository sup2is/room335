package me.sup2is.room335.domain.room;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class RoomRepositoryTest {

    final RoomRepository roomRepository;

    @Test
    void 객실_생성() {
        //given
        final RoomImage roomImage1 = RoomImage.builder()
                .roomImageUrl("url1")
                .isMain(true)
                .build();

        final RoomImage roomImage2 = RoomImage.builder()
                .roomImageUrl("url2")
                .isMain(false)
                .build();

        final Room room = Room.builder()
                .roomFloor(1)
                .roomName("")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomNumber("101")
                .build();

        room.addRoomImages(roomImage1);
        room.addRoomImages(roomImage2);

        //when
        this.roomRepository.save(room);

        //then
        final Room findRoom = this.roomRepository.findById(room.getId()).get();
        assertThat(room).isEqualTo(findRoom);
    }

    @Test
    void roomNumber로_객실_조회() {
        //given
        final Room room = Room.builder()
                .roomNumber("roomNumber")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomName("roomName")
                .roomFloor(1)
                .build();

        this.roomRepository.save(room);

        //when
        final Room findRoom = this.roomRepository.findByRoomNumber(room.getRoomNumber()).get();

        //then
        assertThat(room).isEqualTo(findRoom);
    }

}