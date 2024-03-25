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
    /**
     * тип продукта
     */
    private String productType;
    /**
     * количество единиц
     */
    private int amount;
    /**
     * путь к файлу модели
     */
    private String modelFileName;
    /**
     * подробное описание продукта
     */
    private String description;
    /**
     * цена за единицу
     */
    private double priceForOne;
}
