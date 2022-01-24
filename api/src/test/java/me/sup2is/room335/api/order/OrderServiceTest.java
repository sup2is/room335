package me.sup2is.room335.api.order;

import me.sup2is.room335.api.order.dto.OrderCreateDto;
import me.sup2is.room335.api.order.dto.OrderDto;
import me.sup2is.room335.domain.member.Member;
import me.sup2is.room335.domain.member.MemberRepository;
import me.sup2is.room335.domain.model.Money;
import me.sup2is.room335.domain.order.Order;
import me.sup2is.room335.domain.order.OrderRepository;
import me.sup2is.room335.domain.room.Room;
import me.sup2is.room335.domain.room.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    OrderValidator orderValidator;

    @Test
    void 주문_생성() {
        //given
        long roomId = 1L;
        long memberId = 2L;
        OrderCreateDto.Request request = OrderCreateDto.Request
                .builder()
                .fromDate(LocalDate.of(2022,1,1))
                .toDate(LocalDate.of(2022,1,3))
                .roomId(roomId)
                .memberId(memberId)
                .build();

        given(roomRepository.findById(1L))
                .willReturn(Optional.of(Room.builder()
                        .price(Money.wons(100))
                        .build())
                );

        given(memberRepository.findById(memberId))
                .willReturn(Optional.of(Member.builder().build()));

        //when
        Order order = orderService.createOrder(request);

        //then
        assertThat(order.getTotalPrice()).isEqualTo(Money.wons(200));

        then(orderRepository).should().save(any(Order.class));
    }

    @Test
    void 주문_조회() {
        //given
        long orderId = 1L;
        Order order = Order.builder()
                .memberId(1L)
                .roomId(2L)
                .fromDate(LocalDate.of(2022, 1, 3))
                .toDate(LocalDate.of(2022, 1, 5))
                .totalPrice(Money.wons(1000))
                .build();

        given(orderRepository.findById(orderId))
                .willReturn(Optional.of(order));

        //when
        OrderDto.Response response = orderService.getOrder(orderId);

        //then
        assertThat(response.getFromDate()).isEqualTo(order.getFromDate());
        assertThat(response.getToDate()).isEqualTo(order.getToDate());
        assertThat(response.getMemberId()).isEqualTo(order.getMemberId());
        assertThat(response.getRoomId()).isEqualTo(order.getRoomId());
        assertThat(response.getOrderStateType()).isEqualTo(order.getOrderStateType());
        assertThat(response.getTotalPrice()).isEqualTo(order.getTotalPrice());
    }

}