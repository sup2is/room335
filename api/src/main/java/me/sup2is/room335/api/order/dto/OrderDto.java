package me.sup2is.room335.api.order.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.order.Order;
import me.sup2is.room335.domain.order.OrderStateType;

import java.time.LocalDate;

public class OrderDto {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class Response {

        private Long roomId;
        private Long memberId;
        private OrderStateType orderStateType;
        private LocalDate fromDate;
        private LocalDate toDate;
        private Money totalPrice;

        public static Response of(Order order) {
            return Response.builder()
                    .fromDate(order.getFromDate())
                    .toDate(order.getToDate())
                    .orderStateType(order.getOrderStateType())
                    .memberId(order.getMemberId())
                    .roomId(order.getRoomId())
                    .totalPrice(order.getTotalPrice())
                    .build();
        }
    }
}
