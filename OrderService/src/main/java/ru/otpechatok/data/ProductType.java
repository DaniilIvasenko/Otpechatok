package ru.otpechatok.data;

import lombok.Getter;

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
}