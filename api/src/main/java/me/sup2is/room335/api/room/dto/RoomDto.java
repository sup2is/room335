package me.sup2is.room335.api.room.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.sup2is.room335.domain.room.RoomType;
import me.sup2is.room335.redis.domain.room.RoomCache;

import java.math.BigDecimal;

public class RoomDto {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class Response {
        private final Long roomId;
        private final String roomName;
        private final String roomNumber;
        private final Integer roomFloor;
        private final RoomType roomType;
        private final String description;
        private final BigDecimal price;

        public static Response of(final RoomCache room) {
            return Response.builder()
                    .roomId(room.getId())
                    .roomFloor(room.getRoomFloor())
                    .roomName(room.getRoomName())
                    .roomType(RoomType.valueOf(room.getRoomType()))
                    .roomNumber(room.getRoomNumber())
                    .description(room.getDescription())
                    .price(room.getPrice())
                    .build();
        }
    }
}
