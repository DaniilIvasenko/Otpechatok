package ru.otpechatok.data;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;


@Getter
public enum ProductType {
    STAMP ("печать"),
    BUSINESS_CARD ("визитка");


    private  String productType;

    ProductType(String productType) {
        this.productType = productType;
    }
}
