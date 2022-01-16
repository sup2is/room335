package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;

    private final RoomValidator roomValidator;

    @Transactional
    public void createRoom(RoomCreateDto.Request request) {
        Room room = request.toEntity();

        roomValidator.checkUniqueRoom(room);

        roomRepository.save(room);
    }

}
