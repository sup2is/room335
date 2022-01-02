package me.sup2is.room335.domain.order;

import lombok.RequiredArgsConstructor;
import me.sup2is.room335.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class OrderRepositoryTest {
    
    final OrderRepository orderRepository;

    @Test
    void 주문_생성() {
        //given
        Order order = Order.builder()
                .roomId(1L)
                .userId(1L)
                .fromDate(LocalDate.of(2022, 1, 1))
                .toDate(LocalDate.of(2022, 1, 2))
                .totalPrice(Money.ZERO)
                .build();

        //when
        orderRepository.save(order);

        //then
        Order findOrder = orderRepository.findById(order.getId()).get();
        assertThat(order).isEqualTo(findOrder);
    }

}