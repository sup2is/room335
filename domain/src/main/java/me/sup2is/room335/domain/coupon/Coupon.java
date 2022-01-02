package me.sup2is.room335.domain.coupon;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sup2is.room335.domain.model.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    private Integer disCountPercent;
    private LocalDateTime expireAt;

    @Builder
    private Coupon(final Integer disCountPercent, final LocalDateTime expireAt) {
        this.disCountPercent = disCountPercent;
        this.expireAt = expireAt;
    }
}
