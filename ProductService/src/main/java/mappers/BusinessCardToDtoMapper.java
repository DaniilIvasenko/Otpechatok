package mappers;

import dto.BusinessCardDTO;
import dto.StampDTO;
import org.springframework.stereotype.Component;
import ru.otpechatok.data.ProductType;
import ru.otpechatok.data.businessCard.BusinessCard;
import ru.otpechatok.data.businessCard.BusinessCardSize;
import ru.otpechatok.data.businessCard.MaterialType;
import ru.otpechatok.data.stamp.Stamp;
import ru.otpechatok.data.stamp.StampBodyType;
import ru.otpechatok.data.stamp.StampSize;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("BusinessCardMapper")
public class BusinessCardToDtoMapper implements iProductMapper<BusinessCard, BusinessCardDTO> {
    @Override
    public BusinessCardDTO formEntityToDTO(BusinessCard stamp) {
        BusinessCardDTO productDTO = new BusinessCardDTO();
        productDTO.setId(stamp.getId());
        productDTO.setPrice(stamp.getPrice());
        productDTO.setDescription(stamp.getDescription());
        productDTO.setImageFileName(stamp.getImageFileName());
        productDTO.setProductType(stamp.getProductType().getProductType());
        List<String> sizes = stamp.getSizes().stream().map(x -> x.getSize()).collect(Collectors.toList());
        productDTO.setSize(sizes);
        List<String> materialTypes = stamp.getMaterialTypes().stream().map(x -> x.getMaterialType()).collect(Collectors.toList());
        productDTO.setMaterialTypes(materialTypes);
        return productDTO;
    }

    @Override
    public BusinessCard fromDtoToEntity(BusinessCardDTO businessCardDTO) {
        BusinessCard businessCard = new BusinessCard();
        businessCard.setId(businessCardDTO.getId());
        businessCard.setPrice(businessCardDTO.getPrice());
        businessCard.setDescription(businessCardDTO.getDescription());
        businessCard.setImageFileName(businessCardDTO.getImageFileName());
        ProductType productType = Arrays.stream(ProductType.values())
                .filter(x -> x.getProductType().equals(businessCardDTO.getProductType()))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
        businessCard.setProductType(productType);
        List<BusinessCardSize> sizes = businessCardDTO.getSize().stream().map(x -> new BusinessCardSize(x, businessCard)).collect(Collectors.toList());
        businessCard.setSizes(sizes);
        List<MaterialType> materialTypes = businessCardDTO.getMaterialTypes().stream().map(x -> new MaterialType(x, businessCard)).collect(Collectors.toList());
        businessCard.setMaterialTypes(materialTypes);
        return businessCard;
    }
}
