package ru.otpechatok.dto;

import jakarta.persistence.*;
import lombok.Data;
import ru.otpechatok.data.Order;
import ru.otpechatok.data.ProductType;

import java.math.BigDecimal;

/**
 * DTO для передачи списка товаров в заказе
 */
@Data
public class OrderDetailsDTO {
    private String productType;
    private  int amount;
    private  String modelFileName;
    private  String description;
    private double priceForOne;
}
