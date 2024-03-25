package ru.otpechatok.data.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.otpechatok.data.ProductType;
import ru.otpechatok.data.Status;

import java.util.Arrays;

/**
 * преобразовывает ProductType в строку для сохранения в БД и наоборот
 */
@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductType, String> {
    @Override
    public String convertToDatabaseColumn(ProductType attribute) {
        if (attribute != null) {
            return attribute.getProductType();
        }
        return null;
    }


    @Override
    public ProductType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Arrays.stream(ProductType.values())
                .filter(x -> x.getProductType().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
