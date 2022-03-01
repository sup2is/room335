package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.api.room.dto.RoomDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomCommandService roomCommandService;
    private final RoomQueryService roomQueryService;

    @PostMapping(EndPoints.ROOM_ROOT)
    public void createRoom(@Valid @RequestBody final RoomCreateDto.Request request) {
        roomCommandService.createRoom(request);
    }

    @GetMapping(EndPoints.ROOM_GET)
    public RoomDto.Response getRoom(@PathVariable("roomId") final Long roomId) {
        return roomQueryService.getRoom(roomId);
    }

}
