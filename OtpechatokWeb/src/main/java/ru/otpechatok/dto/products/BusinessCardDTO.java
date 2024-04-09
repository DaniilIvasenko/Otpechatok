package ru.otpechatok.dto.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class BusinessCardDTO extends ProductDTO{
    private List<String> size;
    private  List<String> materialTypes;


    public BusinessCardDTO(Long id, BigDecimal price, String description, String imageFileName, String productType, List<String> size, List<String> materialTypes) {
        super(id, price, description, imageFileName, productType);
        this.size = size;
        this.materialTypes = materialTypes;
    }

    public BusinessCardDTO(List<String> size, List<String> materialTypes) {
        this.size = size;
        this.materialTypes = materialTypes;
    }

    @Override
    public String toString() {
        return "BusinessCardDTO{" +
                "id=" + super.getId() +
                ", price="+ super.getPrice()+
                ", description="+ super.getDescription()+
                ", imageFileName="+ super.getImageFileName()+
                ", productType="+ super.getProductType()+
                ", size=" + size +
                ", materialTypes=" + materialTypes +
                '}';
    }
}
