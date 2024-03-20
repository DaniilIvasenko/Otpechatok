package ru.otpechatok.data;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;


/**
 * конвертер для сохранения Enum ProductType в БД в строковом виде
 */
@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductType, String> {

        @Override
        public String convertToDatabaseColumn(ProductType attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.getProductType();
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
