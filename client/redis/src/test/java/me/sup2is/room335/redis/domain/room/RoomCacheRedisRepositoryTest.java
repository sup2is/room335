package me.sup2is.room335.redis.domain.room;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class RoomCacheRedisRepositoryTest {

    final RoomRedisRepository roomRedisRepository;

    @Test
    void 객실_생성_for_redis() {
        //given
        final RoomCache roomCache = RoomCache.builder()
                .id(1L)
                .roomFloor(1)
                .roomName("roomName")
                .roomType("SINGLE_BED_ROOM")
                .roomNumber("roomNumber")
                .description("description")
                .price(BigDecimal.ONE)
                .roomImages(List.of("1", "2"))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        //when
        final RoomCache saved = roomRedisRepository.save(roomCache);

        //then
        assertThat(saved).isNotNull();
    }

}