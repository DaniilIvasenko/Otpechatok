package ru.otpechatok.controller;

import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otpechatok.data.Product;
import ru.otpechatok.data.ProductType;
import ru.otpechatok.exceptions.ProductTypeNotFoundException;
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
    ResponseEntity<List<ProductDTO>> getAllProducts() {
        ResponseEntity<List<ProductDTO>> response = new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
        return response;
    }


    /**
     * получить список продукции определенного типа с возможностью сортировки
     * @param productTypeString требуемый тип продукта
     * @param sortField
     * @param sortType
     * @return
     */
    @GetMapping("/{productTypeString}")
    ResponseEntity<List<ProductDTO>> findAllProductsByTypeAndSort(
            @PathVariable String productTypeString,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortType) {
        ResponseEntity<List<ProductDTO>> response;
        ProductType productType = null;
        try {
            productType = ProductType.createProductTypeFromString(productTypeString);
        } catch (ProductTypeNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return response;
        }

        List<ProductDTO> products;
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
