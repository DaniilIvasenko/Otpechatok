package ru.otpechatok.data;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;
import ru.otpechatok.data.businessCard.BusinessCard;
import ru.otpechatok.data.stamp.Stamp;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class ProductDeserializer extends StdDeserializer<Product> {
    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public Product deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ProductType productType = ProductType.valueOf(node.get("productType").asText());
        switch (productType){
            case STAMP -> {
                String description = node.get("description").asText();
                BigDecimal price = new BigDecimal(node.get("price").asDouble());
                String imageFileName = node.get("imageFileName").asText();
                //todo сделать десериализацию для списков sizes и bodyTypes

                return  new Stamp(price, description, imageFileName, null/*список sizes*/, null/*список bodyTypes*/);
            }
            case BUSINESS_CARD -> {
                String description = node.get("description").asText();
                BigDecimal price = new BigDecimal(node.get("price").asDouble());
                String imageFileName = node.get("imageFileName").asText();
                //todo сделать десериализацию для списков sizes и materialTypes
                return  new BusinessCard(price, description, imageFileName, null/*список sizes*/, null/*список materialTypes*/);
            }
        }

        return null ;

    }
}
