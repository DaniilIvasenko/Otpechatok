package ru.otpechatok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otpechatok.data.Product;
import ru.otpechatok.data.ProductType;
import ru.otpechatok.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    /**
     * получить список всех продуктов
     * @return ответ со списком продуктов
     */
    @GetMapping()
    ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<Product>> response = new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
        return response;
    }


    /**
     * получить список продукции определенного типа с возможностью сортировки
     * @param productType требуемый тип продукта
     * @param sortField
     * @param sortType
     * @return
     */
    @GetMapping("/{productType}")
    ResponseEntity<List<Product>> findAllProductsByTypeAndSort(
            @PathVariable ProductType productType,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortType) {
        ResponseEntity<List<Product>> response;
        List<Product> products;
        if (sortField != null) {
            if (sortType != null && (sortType.trim().toUpperCase().equals("ASC") || sortType.trim().toUpperCase().equals("DESC"))) {
                if (sortType.trim().toUpperCase().equals("ASC")) {
                    products = productService.findAllByTypeAndOrderByFieldASC(productType, sortField);
                } else {
                    products = productService.findAllByTypeAndOrderByFieldDESC(productType, sortField);
                }
            } else {
                products = productService.findAllByTypeAndOrderByFieldASC(productType, sortField);
            }
        } else {
            products = productService.findAllByProductType(productType);
        }
        response = new ResponseEntity<>(products, HttpStatus.OK);
        return response;
    }
}
