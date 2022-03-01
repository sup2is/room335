package me.sup2is.room335.api.room.dto;

import lombok.Builder;
import lombok.Getter;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.room.RoomType;

@Builder
@Getter
public class RoomCreationMessage {

    private final Long roomId;
    private final String roomName;
    private final String roomNumber;
    private final String description;
    private final Integer roomFloor;
    private final RoomType roomType;
    private final Money price;

}
