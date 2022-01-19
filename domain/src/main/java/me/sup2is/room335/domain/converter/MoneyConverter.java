package me.sup2is.room335.domain.converter;

import me.sup2is.room335.domain.model.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
    @Override
    public Long convertToDatabaseColumn(Money money) {
        return money == null ? null : money.getValue().longValue();
    }

    @Override
    public Money convertToEntityAttribute(Long value) {
        return Money.wons(value == null ? 0 : value);
    }
}