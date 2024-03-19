package ru.otpechatok.data.businessCard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otpechatok.data.Product;
import ru.otpechatok.data.ProductType;
import ru.otpechatok.data.stamp.Stamp;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "business_card")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class BusinessCard extends Product {
    @OneToMany(mappedBy = "businessCard")
    @JsonManagedReference
    private  List<BusinessCardSize> sizes;
    @OneToMany(mappedBy = "businessCard")
    private List<MaterialType> materialTypes;



    public BusinessCard(BigDecimal price, String description, String imageFileName, List<BusinessCardSize> sizes, List<MaterialType> materialTypes) {
        super( price, description, imageFileName);
        super.setProductType(ProductType.BUSINESS_CARD);
        this.sizes = sizes;
        this.materialTypes = materialTypes;
    }

    public BusinessCard() {
        super.setProductType(ProductType.BUSINESS_CARD);
    }

    @Override
    public BusinessCard getInstance() {
        return this;
    }
}


