package me.sup2is.room335.api.order;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractControllerTestConfig;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.order.dto.OrderCreateDto;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class OrderControllerTest extends AbstractControllerTestConfig {

    @Test
    void 주문_생성() throws Exception {
        //given
        OrderCreateDto.Request request = OrderCreateDto.Request
                .builder()
                .fromDate(LocalDate.of(2022,1,1))
                .toDate(LocalDate.of(2022,1,3))
                .roomId(1L)
                .memberId(2L)
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(
                post(EndPoints.ORDER_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andDo(
                        document("order/create")
                );
    }

}