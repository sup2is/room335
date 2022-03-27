package me.sup2is.room335.redis.domain.searchableroom;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class SearchableRoomRepositoryTest {

    final SearchableRoomRepository searchableRoomRepository;

    @Test
    void 날짜별_검색가능한_객실목록생성_for_redis() {
        //given
        final SearchableRoom searchableRoom = SearchableRoom.builder()
                .date(LocalDate.of(2022, 2, 20).toString())
                .build();

        searchableRoom.addRoomId(1L);
        searchableRoom.addRoomId(2L);
        searchableRoom.addRoomId(3L);

        //when
        final SearchableRoom save = searchableRoomRepository.save(searchableRoom);

        //then
        assertThat(save).isNotNull();
    }

}