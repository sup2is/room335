package me.sup2is.room335.api.room.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomImage;
import me.sup2is.room335.domain.room.RoomType;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDto {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class Response {
        private String roomName;
        private String roomNumber;
        private Integer roomFloor;
        private RoomType roomType;
        private String description;
        private List<String> roomImages;

        public static Response of(final Room room) {
            return Response.builder()
                    .roomFloor(room.getRoomFloor())
                    .roomName(room.getRoomName())
                    .roomType(room.getRoomType())
                    .roomNumber(room.getRoomNumber())
                    .description(room.getDescription())
                    .roomImages(room.getRoomImages()
                            .stream()
                            .map(RoomImage::getRoomImageUrl)
                            .collect(Collectors.toList())
                    )
                    .build();
        }
    }
}
