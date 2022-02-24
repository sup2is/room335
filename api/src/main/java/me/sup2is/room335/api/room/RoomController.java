package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.api.room.dto.RoomDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(EndPoints.ROOM_ROOT)
    public void createRoom(@RequestBody RoomCreateDto.Request request) {
        roomService.createRoom(request);
    }

    @GetMapping(EndPoints.ROOM_GET)
    public RoomDto.Response getRoom(@PathVariable("roomId") Long roomId) {
        return roomService.getRoom(roomId);
    }

}
