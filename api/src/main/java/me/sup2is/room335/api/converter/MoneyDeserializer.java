package me.sup2is.room335.api.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.sup2is.room335.domain.model.Money;

import java.io.IOException;

public class MoneyDeserializer extends JsonDeserializer<Money> {

    @Override
    public Money deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        return Money.wons(jsonParser.getLongValue());
    }
}
