package me.sup2is.room335.api.order;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.api.order.dto.OrderCreateDto;
import me.sup2is.room335.api.order.dto.OrderDto;
import me.sup2is.room335.domain.member.MemberRepository;
import me.sup2is.room335.domain.order.Order;
import me.sup2is.room335.domain.order.OrderRepository;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final OrderValidator orderValidator;

    @Transactional
    public Order createOrder(OrderCreateDto.Request request) {

        Order order = request.toEntity();

        orderValidator.checkValidOrder(order);

        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(
                () -> new IllegalArgumentException()
        );

        memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new IllegalArgumentException()
        );

        order.calculateTotalPrice(room.getPrice());

        orderRepository.save(order);

        return order;
    }

    public OrderDto.Response getOrder(final Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException()
        );

        return OrderDto.Response.of(order);
    }
}
