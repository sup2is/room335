package me.sup2is.room335.api.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.config.AbstractControllerTestConfig;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.order.dto.OrderCreateDto;
import me.sup2is.room335.api.order.dto.OrderDto;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import me.sup2is.room335.api.room.dto.RoomDto;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.order.Order;
import me.sup2is.room335.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class OrderControllerTest extends AbstractControllerTestConfig {

    private final ObjectMapper objectMapper;

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

    @Test
    void 주문_조회() throws Exception {
        //given
        Order order = Order.builder()
                .memberId(1L)
                .roomId(2L)
                .fromDate(LocalDate.of(2022, 1, 3))
                .toDate(LocalDate.of(2022, 1, 5))
                .totalPrice(Money.wons(1000))
                .build();

        given(orderService.getOrder(anyLong()))
                .willReturn(OrderDto.Response.of(order));

        //when
        ResultActions resultActions = mockMvc.perform(
                get(EndPoints.ORDER_GET, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andDo(
                        document("order/get")
                );

        resultActions.andExpect(jsonPath("$.fromDate").value(order.getFromDate().toString()));
        resultActions.andExpect(jsonPath("$.toDate").value(order.getToDate().toString()));
        resultActions.andExpect(jsonPath("$.orderStateType").value(order.getOrderStateType().toString()));
        resultActions.andExpect(jsonPath("$.memberId").value(order.getMemberId()));
        resultActions.andExpect(jsonPath("$.roomId").value(order.getRoomId()));
        resultActions.andExpect(jsonPath("$.totalPrice").value(order.getTotalPrice().getValue()));
    }

}