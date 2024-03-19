package ru.otpechatok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private  final ProductService productService;

    @GetMapping()
    ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> response = new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
        return  response;
    }


    @PostMapping("add")
    public  void addProduct(){
        Product stamp = new Stamp(new BigDecimal(100), null, "123.img", null, null);
        List<StampSize> sizes =  List.of(new StampSize("10x1", (Stamp) stamp), new StampSize("14x1", (Stamp) stamp));
        List<StampBodyType> bodyTypes = List.of(
                new StampBodyType("круглая", (Stamp) stamp),
                new StampBodyType("круглая", (Stamp) stamp));
        ((Stamp) stamp).setSizes(sizes);
        ((Stamp) stamp).setBodyTypes(bodyTypes);
        stamp = productService.add(stamp);
        System.out.println(stamp.getProductType());

        Product businessCard = new BusinessCard(
                new BigDecimal(11),
                "simple",
                ".img",
                null,
                null);
        List<BusinessCardSize> businessCardSizes =  List.of(
                new BusinessCardSize("10x1", (BusinessCard) businessCard),
                new BusinessCardSize("14x1", (BusinessCard) businessCard));
        List<MaterialType> materialTypes = List.of(
                new MaterialType("бумага", (BusinessCard) businessCard),
                new MaterialType("картон", (BusinessCard) businessCard));
        ((BusinessCard) businessCard).setSizes(businessCardSizes);
        ((BusinessCard) businessCard).setMaterialTypes(materialTypes);
        businessCard = productService.add(businessCard);
        System.out.println(businessCard.getProductType());
    }
}
