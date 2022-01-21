package me.sup2is.room335.api.order.dto;

import lombok.Builder;
import lombok.Getter;
import me.sup2is.room335.domain.order.Order;

import java.time.LocalDate;

public class OrderCreateDto {

    @Builder
    @Getter
    public static class Request {

        private Long roomId;
        private Long memberId;
        private LocalDate fromDate;
        private LocalDate toDate;

        public Order toEntity() {
            return Order.builder()
                    .fromDate(fromDate)
                    .toDate(toDate)
                    .roomId(roomId)
                    .memberId(memberId)
                    .build();
        }
    }
}
