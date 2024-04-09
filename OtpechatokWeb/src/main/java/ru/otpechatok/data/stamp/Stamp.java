package ru.otpechatok.data.stamp;

import lombok.NoArgsConstructor;
import ru.otpechatok.data.Product;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
public class Stamp extends Product {
    private List<StampSize> sizes;
    private List<BodyType> bodyTypes;

    public Stamp(BigDecimal price, String description, String imageFileName) {
        super(price, description, imageFileName);
    }

    public Stamp(List<StampSize> stampSizes) {
        this.sizes = stampSizes;
    }

    public Stamp(BigDecimal price, String description, String imageFileName, List<StampSize> stampSizes) {
        super(price, description, imageFileName);
        this.sizes = stampSizes;
    }



}
