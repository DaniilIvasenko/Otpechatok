package ru.otpechatok.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/*
родитель для всех товаров
 */
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Transient
    private ProductType productType;


    public Product(BigDecimal price, String description, String imageFileName) {
        this.price = price;
        this.description = description;
        this.imageFileName = imageFileName;
    }



    /**
     * получить экземпляр класса наследника
     *
     * @return наследник класса Product
     */
    public abstract <T extends Product> T getInstance();
}
