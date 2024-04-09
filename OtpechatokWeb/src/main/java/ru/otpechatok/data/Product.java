package ru.otpechatok.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    /**
     * цена за единицу товара
     */
    private BigDecimal price;
    /**
     * описание товара
     */
    private String description;
    /**
     * путь к изображению товара.
     */
    private String imageFileName;
    /**
     * Тип продукта.
     * При инициализации наследника подставляется значение соответствующее типу наследника
     */
    private ProductType productType;


    public Product(BigDecimal price, String description, String imageFileName) {
        this.price = price;
        this.description = description;
        this.imageFileName = imageFileName;
    }

}
