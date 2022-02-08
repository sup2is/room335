package me.sup2is.room335.api.room.dto;

import lombok.Builder;
import lombok.Getter;
import me.sup2is.room335.domain.model.Money;

import java.math.BigDecimal;

@Builder
@Getter
public class RoomCreationMessage {

    private Long roomId;
    private String roomName;
    private String roomNumber;
    private Integer roomFloor;
    private BigDecimal price;

}
