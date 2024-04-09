package mappers;

import dto.ProductDTO;
import org.springframework.stereotype.Component;
import ru.otpechatok.data.Product;

public interface iProductMapper <T extends Product, V extends  ProductDTO>{
    V formEntityToDTO(T product);

    T fromDtoToEntity(V productDTO);
}
