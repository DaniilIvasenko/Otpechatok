package dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otpechatok.data.stamp.Stamp;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class StampDTO extends ProductDTO{
    private List<String> size;
    private  List<String> bodyTypes;


    public StampDTO(Long id, BigDecimal price, String description, String imageFileName, String productType, List<String> size, List<String> bodyTypes) {
        super(id, price, description, imageFileName, productType);
        this.size = size;
        this.bodyTypes = bodyTypes;
    }
}
