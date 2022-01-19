package me.sup2is.room335.api.room.dto;

import lombok.Builder;
import lombok.Getter;
import me.sup2is.room335.domain.member.Member;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class RoomCreateDto {

    @Builder
    @Getter
    public static class Request {

        private String roomName;
        private String roomNumber;
        private Integer roomFloor;
        private RoomType roomType;
        private Money price;

        public Room toEntity() {
            return Room.builder()
                    .roomFloor(roomFloor)
                    .roomName(roomName)
                    .roomNumber(roomNumber)
                    .roomType(roomType)
                    .price(price)
                    .build();
        }
    }

}
