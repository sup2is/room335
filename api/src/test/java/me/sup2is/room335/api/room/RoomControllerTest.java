package me.sup2is.room335.api.room;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractControllerTestConfig;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.api.room.dto.RoomDto;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class RoomControllerTest extends AbstractControllerTestConfig {

    final RoomService roomService;

    @Test
    void 객실_생성() throws Exception {
        //given
        final RoomCreateDto.Request request = RoomCreateDto.Request.builder()
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomNumber("roomNumber")
                .roomName("roomName")
                .roomFloor(1)
                .build();

        //when
        final ResultActions resultActions = this.mockMvc.perform(
                post(EndPoints.ROOM_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(request))
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andDo(
                        document("room/create")
                );
    }


    @Test
    void 객실_조회() throws Exception {
        //given
        final Room room = Room.builder()
                .roomImages(List.of("url"))
                .description("description")
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomName("roomName")
                .roomFloor(1)
                .roomNumber("roomNumber")
                .build();

        given(this.roomService.getRoom(anyLong()))
                .willReturn(RoomDto.Response.of(room));

        //when
        final ResultActions resultActions = this.mockMvc.perform(
                get(EndPoints.ROOM_GET, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andDo(
                        document("room/get")
                );

        resultActions.andExpect(jsonPath("$.roomImages").value(room.getRoomImages()));
        resultActions.andExpect(jsonPath("$.description").value(room.getDescription()));
        resultActions.andExpect(jsonPath("$.roomType").value(room.getRoomType().toString()));
        resultActions.andExpect(jsonPath("$.roomName").value(room.getRoomName()));
        resultActions.andExpect(jsonPath("$.roomFloor").value(room.getRoomFloor()));
        resultActions.andExpect(jsonPath("$.roomNumber").value(room.getRoomNumber()));
    }


}