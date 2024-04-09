package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
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
