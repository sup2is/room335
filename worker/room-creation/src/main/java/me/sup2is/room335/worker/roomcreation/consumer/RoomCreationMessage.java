package me.sup2is.room335.worker.roomcreation.consumer;

import lombok.*;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.room.RoomType;

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
    private Integer roomFloor;
    private RoomType roomType;
    private Money price;

}
