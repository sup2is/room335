package me.sup2is.room335.redis.domain.room;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@RedisHash("room")
public class RoomCache {

    @Id
    private Long id;
    private String roomName;
    private String roomNumber;
    private Integer roomFloor;
    private BigDecimal price;
    private String roomType;
    private String description;
    private List<String> roomImages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    private RoomCache(final Long id,
                      final String roomName,
                      final String roomNumber,
                      final Integer roomFloor,
                      final BigDecimal price,
                      final String roomType,
                      final String description,
                      final List<String> roomImages,
                      final LocalDateTime createdAt,
                      final LocalDateTime updatedAt) {
        this.id = id;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomFloor = roomFloor;
        this.price = price;
        this.roomType = roomType;
        this.description = description;
        this.roomImages = roomImages;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
