package ru.otpechatok.service;

import dto.ProductDTO;
import ru.otpechatok.data.Product;

import java.util.List;

public interface iProductService<T extends  Product> {
    /**
     * добавить продукт в базу данных
     * @param product продукт для добавления
     * @return продукт полученный из БД после добавления
     */
    T add(T product);


    /**
     * получить список всех товаров
     * @return список товаров из БД
     */
    List<ProductDTO> findAll();


    /**
     * сортировка по полю по возрастанию
     *
     * @param fieldName поле по которому будет выполнена сортировка
     * @return отсортированный список
     */
    List<ProductDTO> findAllOrderByFieldASC(String fieldName);


    /**
     * сортировка по полю по убыванию
     * @param fieldName поле по которому будет выполнена сортировка
     * @return отсортированный список
     */
    List<ProductDTO> findAllOrderByFieldDESC(String fieldName);


}
