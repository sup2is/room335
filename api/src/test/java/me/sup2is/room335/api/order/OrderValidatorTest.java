package me.sup2is.room335.api.order;

import me.sup2is.room335.domain.order.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class OrderValidatorTest {

    @InjectMocks
    OrderValidator orderValidator;

    @ParameterizedTest
    @CsvSource({"2022/01/11, 2022/01/11", "2022/01/11, 2022/01/10"})
    void 유효한_fromDate_toDate가_아닌_경우_IllegalArgumentException(final String fromDate, final String toDate) {
        //given
        final Order order = Order.builder()
                .fromDate(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .toDate(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .build();

        //when
        //then
        assertThrows(IllegalArgumentException.class,
                () -> this.orderValidator.checkValidOrder(order)
        );

    }

}