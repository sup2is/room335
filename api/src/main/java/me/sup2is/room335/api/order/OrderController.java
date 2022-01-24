package me.sup2is.room335.api.order;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.order.dto.OrderCreateDto;
import me.sup2is.room335.api.room.dto.RoomCreateDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(EndPoints.ORDER_ROOT)
    public void createOrder(OrderCreateDto.Request request) {
        orderService.createOrder(request);
    }

}
