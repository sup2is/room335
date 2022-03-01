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
public class RoomCommandService {

    private final RoomRepository roomRepository;

    private final RoomValidator roomValidator;

    private final RoomCreationEventPublisher roomCreationEventPublisher;

    @Transactional
    public void createRoom(final RoomCreateDto.Request request) {
        final Room room = request.toEntity();

        this.roomValidator.checkUniqueRoom(room);

        roomRepository.save(room);

        roomCreationEventPublisher.publishRoomCreationEvent(room.getId(), request);
    }

}
