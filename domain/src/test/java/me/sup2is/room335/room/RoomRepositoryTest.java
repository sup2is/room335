package me.sup2is.room335.room;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class RoomRepositoryTest {

    final RoomRepository roomRepository;

    @Test
    void 객실_생성() {
        //given
        Room room = Room.builder()
                .roomFloor(1)
                .roomName("")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomNumber("101")
                .build();

        //when
        roomRepository.save(room);

        //then
        Room findRoom = roomRepository.findById(room.getId()).get();
        assertThat(room).isEqualTo(findRoom);
    }

}