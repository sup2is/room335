package me.sup2is.room335.worker.roomcreation.consumer;

import lombok.*;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.room.RoomType;
import me.sup2is.room335.redis.domain.room.RoomCache;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class RoomCreationMessage {

    private Long roomId;
    private String roomName;
    private String roomNumber;
    private String description;
    private Integer roomFloor;
    private RoomType roomType;
    private Money price;

    public RoomCache toCache() {
        return RoomCache.builder()
                .id(roomId)
                .roomFloor(roomFloor)
                .roomType(roomType.name())
                .roomName(roomName)
                .roomNumber(roomNumber)
                .description(description)
                .price(price.getValue())
                .build();
    }

}
