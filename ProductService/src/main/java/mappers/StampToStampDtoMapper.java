package mappers;

import dto.ProductDTO;
import dto.StampDTO;
import org.springframework.stereotype.Component;
import ru.otpechatok.data.Product;
import ru.otpechatok.data.ProductType;
import ru.otpechatok.data.stamp.Stamp;
import ru.otpechatok.data.stamp.StampBodyType;
import ru.otpechatok.data.stamp.StampSize;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component("StampDtoMapper")
public class StampToStampDtoMapper implements iProductMapper<Stamp, StampDTO> {
    @Override
    public StampDTO formEntityToDTO(Stamp stamp) {
        StampDTO productDTO = new StampDTO();
        productDTO.setId(stamp.getId());
        productDTO.setPrice(stamp.getPrice());
        productDTO.setDescription(stamp.getDescription());
        productDTO.setImageFileName(stamp.getImageFileName());
        productDTO.setProductType(stamp.getProductType().getProductType());
        List<String> sizes = stamp.getSizes().stream().map(x -> x.getSize()).collect(Collectors.toList());
        productDTO.setSize(sizes);
        List<String> bodyTypes = stamp.getBodyTypes().stream().map(x -> x.getBodyType()).collect(Collectors.toList());
        productDTO.setBodyTypes(bodyTypes);
        return productDTO;
    }

    @Override
    public Stamp fromDtoToEntity(StampDTO stampDTO) {
        Stamp stamp = new Stamp();
        stamp.setId(stampDTO.getId());
        stamp.setPrice(stampDTO.getPrice());
        stamp.setDescription(stampDTO.getDescription());
        stamp.setImageFileName(stampDTO.getImageFileName());
        ProductType productType = Arrays.stream(ProductType.values())
                .filter(x -> x.getProductType().equals(stampDTO.getProductType()))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
        stamp.setProductType(productType);
        List<StampSize> sizes = stampDTO.getSize().stream().map(x -> new StampSize(x, stamp)).collect(Collectors.toList());
        stamp.setSizes(sizes);
        List<StampBodyType> bodyTypes = stampDTO.getBodyTypes().stream().map(x -> new StampBodyType(x, stamp)).collect(Collectors.toList());
        stamp.setBodyTypes(bodyTypes);
        return stamp;
    }
}
