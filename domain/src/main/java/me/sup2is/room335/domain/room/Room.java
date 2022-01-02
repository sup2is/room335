package me.sup2is.room335.domain.room;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sup2is.room335.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseEntity {

    private String roomName;
    private String roomNumber;
    private Integer roomFloor;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private String description;

    @Builder
    private Room(final String roomName, final String roomNumber, final Integer roomFloor, final RoomType roomType, final String description) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomFloor = roomFloor;
        this.roomType = roomType;
        this.description = description;
    }
}