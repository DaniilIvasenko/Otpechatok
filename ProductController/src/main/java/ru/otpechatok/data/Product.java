package ru.otpechatok.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.NoRepositoryBean;

import java.math.BigDecimal;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@JsonDeserialize(using = ProductDeserializer.class)
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private BigDecimal price;
    private  String description;
    private  String imageFileName;
    @Transient
    private  ProductType productType;

    public Product( BigDecimal price, String description, String imageFileName) {
        this.price = price;
        this.description = description;
        this.imageFileName = imageFileName;
    }

    public abstract <T extends Product> T getInstance();
}
