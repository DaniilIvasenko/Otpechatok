package ru.otpechatok.data;

import lombok.Getter;
import ru.otpechatok.exceptions.ProductTypeNotFoundException;

import java.util.Arrays;

/**
 * тип продукта
 */
@Getter
public enum ProductType {
    STAMP("печать"),
    BUSINESS_CARD("визитка");

    private String productType;

    ProductType(String productType) {
        this.productType = productType;
    }

    public static ProductType createProductTypeFromString(String productTypeString) throws ProductTypeNotFoundException {
        ProductType productType;
        try {
            productType = ProductType.valueOf(productTypeString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            productType = Arrays.stream(ProductType.values())
                    .filter(x->x.getProductType().equals(productTypeString.trim().toLowerCase()))
                    .findFirst()
                    .orElseThrow(ProductTypeNotFoundException::new);

        }
        return productType;
    }
}
