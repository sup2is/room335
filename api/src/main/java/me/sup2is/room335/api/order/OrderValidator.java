package me.sup2is.room335.api.order;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.domain.order.Order;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    public void checkValidOrder(Order newer) {
        long days = ChronoUnit.DAYS.between(newer.getFromDate(), newer.getToDate());

        if(days <= 0) {
            throw new IllegalArgumentException();
        }
    }

}
