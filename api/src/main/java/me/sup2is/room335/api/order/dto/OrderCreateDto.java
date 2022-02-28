package me.sup2is.room335.api.order.dto;

import lombok.Builder;
import lombok.Getter;
import me.sup2is.room335.domain.order.Order;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class OrderCreateDto {

    @Builder
    @Getter
    public static class Request {

        @NotNull
        private final Long roomId;
        @NotNull
        private final Long memberId;
        @NotNull
        private final LocalDate fromDate;
        @NotNull
        private final LocalDate toDate;

        public Order toEntity() {
            return Order.builder()
                    .fromDate(this.fromDate)
                    .toDate(this.toDate)
                    .roomId(this.roomId)
                    .memberId(this.memberId)
                    .build();
        }
    }
}
