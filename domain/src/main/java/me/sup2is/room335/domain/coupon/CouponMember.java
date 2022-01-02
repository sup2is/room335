package me.sup2is.room335.domain.coupon;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sup2is.room335.domain.model.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponMember extends BaseEntity {

    private Long memberId;
    private Long couponId;

    @Builder
    private CouponMember(final Long memberId, final Long couponId) {
        this.memberId = memberId;
        this.couponId = couponId;
    }
}
