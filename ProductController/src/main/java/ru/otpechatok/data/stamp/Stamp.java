package ru.otpechatok.data.stamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otpechatok.data.Product;
import ru.otpechatok.data.ProductType;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "stamp")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Stamp extends Product {
    @OneToMany(mappedBy = "stamp")
    @JsonManagedReference
    private List<StampSize> sizes;

    @OneToMany(mappedBy = "stamp")
    List<StampBodyType> bodyTypes;

    public Stamp(BigDecimal price, String description, String imageFileName, List<StampSize> sizes, List<StampBodyType> bodyTypes) {
        super( price, description, imageFileName);
        super.setProductType(ProductType.STAMP);
        this.sizes = sizes;
        this.bodyTypes = bodyTypes;
    }

    public Stamp() {
        super.setProductType(ProductType.STAMP);
    }

    @Override
    public Stamp getInstance() {
        return this;
    }


}
