package me.sup2is.room335.domain.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(of = "value")
public class Money implements Serializable, Comparable<Money> {
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    private static final long serialVersionUID = -2336570002559705307L;

    private final BigDecimal value;

    protected Money() {
        value = BigDecimal.ZERO;
    }

    private Money(BigDecimal value) {
        this.value = BigDecimal.valueOf(value.longValue());
    }

    public static Money wons(Integer value) {
        if (value == null || value <= 0) {
            return Money.ZERO;
        }
        return wons(value.longValue());
    }

    public static Money wons(long value) {
        if (value <= 0) {
            return Money.ZERO;
        }
        return new Money(BigDecimal.valueOf(value));
    }

    public BigDecimal getValue() {
        return BigDecimal.valueOf(value.longValue());
    }

    public boolean isGreaterThan(Money other) {
        return compareTo(other) > 0;
    }

    public boolean isGreaterThanOrEquals(Money other) {
        return isGreaterThan(other) || equals(other);
    }

    public boolean isLessThan(Money other) {
        return compareTo(other) < 0;
    }

    public boolean isLessThanOrEquals(Money other) {
        return isLessThan(other) || equals(other);
    }

    @Override
    public int compareTo(Money other) {
        if (other == null) {
            return 1;
        }

        return value.compareTo(other.value);
    }

    @Override
    public String toString() {
        return value == null ? "NaN" : value.toString();
    }
}
