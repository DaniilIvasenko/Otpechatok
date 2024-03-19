package ru.otpechatok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpechatok.data.Product;
import ru.otpechatok.data.ProductType;
import ru.otpechatok.data.stamp.Stamp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final StampService stampService;
    private final BusinessCardService businessCardService;

    /**
     * найти все продукты всех типов в БД
     * @return список продуктов
     */
    public List<Product> findAllProducts(){
        List<Product> products = new ArrayList<>();
        products.addAll(stampService.findAll());
        products.addAll(businessCardService.findAll());
        return products;
    }


    /**
     * найти все продукты в БД определенного типа
     * @param productType тип продукта для поиска
     * @return список продуктов из БД
     */
    public List<Product> findAllByProductType(ProductType productType){
        iProductService service = getServiceByProductType(productType);
        return  service.findAll();
    }

    /**
     * поиск и сортировка продуктов определенного типа по возрастанию
     * @param productType тип продукта
     * @param field поле для сортировки
     * @return список продуктов
     */
    public List<Product> findAllByTypeAndOrderByFieldASC(ProductType productType, String field){
        iProductService service = getServiceByProductType(productType);
        return service.findAllOrderByFieldASC(field);
    }


    /**
     * поиск и сортировка продуктов определенного типа по убыванию
     * @param productType тип продукта
     * @param field поле для сортировки
     * @return список продуктов
     */
    public List<Product> findAllByTypeAndOrderByFieldDESC(ProductType productType, String field){
        iProductService service = getServiceByProductType(productType);
        return service.findAllOrderByFieldDESC(field);
    }


    /**
     * сохранение продукта в БД
     * @param product Продукт для сохранения
     * @return экземпляр продукта из БД с присвоенными ID
     */
    public Product add(Product product) {
        iProductService service = getServiceByProductType(product.getProductType());
        if (service==null){
            throw new RuntimeException("Невозможно подобрать сервис для указанного продукта");
        }
        return service.add(product.getInstance());
    }


    /**
     * Возвращает сервис, основываясь на типе продукта переданного в качестве аргумента метода
     *
     * @param productType тип продукта в соответствии с типом которого будет подобран подходящий сервис
     * @return реализацию iProductService
     */
    iProductService<?> getServiceByProductType(ProductType productType) {
        switch (productType) {
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
