package me.sup2is.room335.domain.coupon;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class CouponMemberRepositoryTest {

    final CouponMemberRepository couponMemberRepository;

    @Test
    void 쿠폰_멤버_생성() {
        //given
        CouponMember couponMember = CouponMember.builder()
                .couponId(1L)
                .memberId(1L)
                .build();

        //when
        couponMemberRepository.save(couponMember);

        //then
        CouponMember findCouponMember = couponMemberRepository.findById(couponMember.getId()).get();
        assertThat(couponMember).isEqualTo(findCouponMember);
    }

}