package ru.otpechatok.data;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class OrderDetailJSONParser extends StdDeserializer<OrderDetails> {


    public OrderDetailJSONParser() {
        this(null);
    }

    public OrderDetailJSONParser(Class<?> vc) {
        super(vc);
    }

    @Override
    public OrderDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        //todo сделать десериализацию
        return null;

    }
}



