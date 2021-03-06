package me.sup2is.room335.domain.room;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sup2is.room335.domain.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomImage extends BaseEntity {

    @ManyToOne
    private Room room;
    private String roomImageUrl;
    private Boolean isMain;
    private Integer orders;

    @Builder
    private RoomImage(final String roomImageUrl, final Boolean isMain, final Integer orders) {
        this.roomImageUrl = roomImageUrl;
        this.isMain = isMain;
        this.orders = orders;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
