package ru.otpechatok.dto.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private  Long id;
    private BigDecimal price;
    private  String description;
    private  String imageFileName;
    private  String productType;
}
