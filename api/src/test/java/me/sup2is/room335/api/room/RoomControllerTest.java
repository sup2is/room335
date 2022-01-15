package me.sup2is.room335.api.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractControllerTestConfig;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.member.dto.MemberCreateDto;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class RoomControllerTest extends AbstractControllerTestConfig {

    final RoomService roomService;

    @Test
    void 객실_생성() throws Exception {
        //given
        RoomCreateDto.Request request = RoomCreateDto.Request.builder()
                .roomType(RoomType.SINGLE_BED_ROOM)
                .roomNumber("roomNumber")
                .roomName("roomName")
                .roomFloor(1)
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(
                post(EndPoints.ROOM_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andDo(
                        document("room/create")
                );
    }

}