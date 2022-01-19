package me.sup2is.room335.domain.room;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sup2is.room335.domain.model.BaseEntity;
import me.sup2is.room335.domain.model.Money;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseEntity {

    private String roomName;
    private String roomNumber;
    private Integer roomFloor;
    private Money price;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private String description;

    @OneToMany(mappedBy = "room")
    private List<RoomImage> roomImages;

    @Builder
    private Room(final String roomName,
                 final String roomNumber,
                 final Integer roomFloor,
                 final RoomType roomType,
                 final String description,
                 final Money price,
                 final List<String> roomImages) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomFloor = roomFloor;
        this.roomType = roomType;
        this.description = description;
        this.price = price;

        this.roomImages = new ArrayList<>();
    }

    public void addRoomImages(final RoomImage roomImage) {
        this.roomImages.add(roomImage);
        roomImage.setRoom(this);
    }

}
