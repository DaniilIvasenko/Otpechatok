package ru.otpechatok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otpechatok.data.Product;
import ru.otpechatok.data.ProductType;
import ru.otpechatok.data.businessCard.BusinessCard;
import ru.otpechatok.data.businessCard.BusinessCardSize;
import ru.otpechatok.data.businessCard.MaterialType;
import ru.otpechatok.data.stamp.Stamp;
import ru.otpechatok.data.stamp.StampBodyType;
import ru.otpechatok.data.stamp.StampSize;
import ru.otpechatok.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<Product>> response = new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
        return response;
    }

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


    @PostMapping("add")
    public void addProduct(@RequestBody Product product) {
        productService.add(product);
    }
}
