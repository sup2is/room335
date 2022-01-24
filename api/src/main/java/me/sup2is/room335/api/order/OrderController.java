package me.sup2is.room335.api.order;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.order.dto.OrderCreateDto;
import me.sup2is.room335.api.order.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(EndPoints.ORDER_GET)
    public OrderDto.Response getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

}
