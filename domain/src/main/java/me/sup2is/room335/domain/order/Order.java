package me.sup2is.room335.domain.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sup2is.room335.domain.model.BaseEntity;
import me.sup2is.room335.domain.model.Money;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {

    private Long roomId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStateType orderStateType;

    private LocalDate fromDate;
    private LocalDate toDate;
    private Money totalPrice;

    @Builder
    private Order(final Long roomId, final Long userId, final LocalDate fromDate, final LocalDate toDate, final Money totalPrice) {
        this.roomId = roomId;
        this.userId = userId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalPrice = totalPrice;

        this.orderStateType = OrderStateType.PREPARED;
    }
}
