package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(EndPoints.ROOM_ROOT)
    public void createRoom(RoomCreateDto.Request request) {
        roomService.createRoom(request);
    }

}
