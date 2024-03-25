package ru.otpechatok.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otpechatok.data.ProductType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;


    @Test
    @DisplayName("service select test")
    void getServiceByProductType() {
        assertEquals(productService.getServiceByProductType(ProductType.STAMP) instanceof StampService, true);
        assertEquals(productService.getServiceByProductType(ProductType.STAMP) instanceof BusinessCardService, false);
        assertEquals(productService.getServiceByProductType(ProductType.BUSINESS_CARD) instanceof BusinessCardService, true);
        assertEquals(productService.getServiceByProductType(ProductType.BUSINESS_CARD) instanceof StampService, false);
    }
}