package me.sup2is.room335.api.order;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.constant.EndPoints;
import me.sup2is.room335.api.order.dto.OrderCreateDto;
import me.sup2is.room335.api.order.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(EndPoints.ORDER_ROOT)
    public void createOrder(@Valid @RequestBody final OrderCreateDto.Request request) {
        this.orderService.createOrder(request);
    }

    @GetMapping(EndPoints.ORDER_GET)
    public OrderDto.Response getOrder(@PathVariable("orderId") final Long orderId) {
        return this.orderService.getOrder(orderId);
    }

}
