package me.sup2is.room335.domain.coupon;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class CouponRepositoryTest {

    final CouponRepository couponRepository;

    @Test
    void 쿠폰_생성() {
        //given
        final Coupon coupon = Coupon.builder()
                .disCountPercent(10)
                .expireAt(LocalDateTime.now())
                .build();

        //when
        this.couponRepository.save(coupon);

        //then
        final Coupon findCoupon = this.couponRepository.findById(coupon.getId()).get();
        assertThat(coupon).isEqualTo(findCoupon);
    }

}