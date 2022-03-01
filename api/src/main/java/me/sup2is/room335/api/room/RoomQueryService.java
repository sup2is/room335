package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.room.dto.RoomDto;
import me.sup2is.room335.redis.domain.room.RoomCache;
import me.sup2is.room335.redis.domain.room.RoomRedisRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomQueryService {

    private final RoomRedisRepository roomRedisRepository;

    public RoomDto.Response getRoom(final Long roomId) {
        final RoomCache roomCache = roomRedisRepository.findById(roomId).orElseThrow(
                () -> new IllegalArgumentException()
        );

        return RoomDto.Response.of(roomCache);
    }
}
