package me.sup2is.room335.api.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomType;

public class RoomCreateDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        private String roomName;
        private String roomNumber;
        private Integer roomFloor;
        private RoomType roomType;
        private Money price;

        public Room toEntity() {
            return Room.builder()
                    .roomFloor(this.roomFloor)
                    .roomName(this.roomName)
                    .roomNumber(this.roomNumber)
                    .roomType(this.roomType)
                    .price(this.price)
                    .build();
        }
    }

}
