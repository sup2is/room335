package me.sup2is.room335.redis.domain.searchableroom;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Getter
@RedisHash("searchable")
public class SearchableRoom {

    @Id
    private final String date;

    @Builder.Default
    private final List<Long> rooms = new ArrayList<>();

    @Builder
    private SearchableRoom(final String date) {
        this.date = date;
    }

    public void addRoomId(final Long roomId) {
        this.rooms.add(roomId);
    }
}
