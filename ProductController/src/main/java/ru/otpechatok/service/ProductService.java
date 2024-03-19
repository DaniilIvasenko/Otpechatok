package ru.otpechatok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpechatok.data.Product;
import ru.otpechatok.data.stamp.Stamp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final StampService stampService;
    private final BusinessCardService businessCardService;

    public List<Product> findAllProducts(){
        List<Product> products = new ArrayList<>();
        products.addAll(stampService.findAll());
        products.addAll(businessCardService.findAll());
        return products;
    }


    /**
     * сохранение продукта в БД
     * @param product Продукт для сохранения
     * @return экземпляр продукта из БД с присвоенными ID
     */
    public Product add(Product product) {
        iProductService service = getServiceByProductType(product);
        if (service==null){
            throw new RuntimeException("Невозможно подобрать сервис для указанного продукта");
        }
        return service.add(product.getInstance());
    }


    /**
     * Возвращает сервис, основываясь на типе продукта переданного в качестве аргумента метода
     *
     * @param product продукт в соответствии с типом которого будет подобран подходящий сервис
     * @return реализацию iProductService
     */
    iProductService<?> getServiceByProductType(Product product) {
        switch (product.getProductType()) {
            case STAMP -> {
                return stampService;
            }
            case BUSINESS_CARD -> {
                return businessCardService;
            }
        }
        return null;
    }

}
