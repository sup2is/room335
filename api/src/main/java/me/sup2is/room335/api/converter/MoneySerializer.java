package me.sup2is.room335.api.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import me.sup2is.room335.domain.model.Money;

import java.io.IOException;

public class MoneySerializer extends JsonSerializer<Money> {

    @Override
    public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeNumber(money.getValue());
    }
}
