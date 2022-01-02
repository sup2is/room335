package me.sup2is.room335.domain.coupon;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class CouponRepositoryTest {

    final CouponRepository couponRepository;

    @Test
    void 쿠폰_생성() {
        //given
        Coupon coupon = Coupon.builder()
                .disCountPercent(10)
                .expireAt(LocalDateTime.now())
                .build();

        //when
        couponRepository.save(coupon);

        //then
        Coupon findCoupon = couponRepository.findById(coupon.getId()).get();
        assertThat(coupon).isEqualTo(findCoupon);
    }

}