package me.sup2is.room335.api.order;

import me.sup2is.room335.api.order.dto.OrderCreateDto;
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

    @Test
    void 주문_생성() {
        //given
        long roomId = 1L;
        long memberId = 2L;
        OrderCreateDto.Request request = OrderCreateDto.Request
                .builder()
                .fromDate(LocalDate.of(2022,1,1))
                .toDate(LocalDate.of(2022,1,2))
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
        orderService.createOrder(request);

        //then
        then(orderRepository).should().save(any(Order.class));
    }

}