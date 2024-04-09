package ru.otpechatok.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otpechatok.dto.products.BusinessCardDTO;
import ru.otpechatok.dto.products.StampDTO;
import ru.otpechatok.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("otpechatok")
@RequiredArgsConstructor
public class WebController {
    private  final ProductService productService;

    /**
     * получить список печатей с сервера
     * @param sortField поле для сортировки (не обязательное)
     * @param sortType тип сортировки ASC или DESC (не обязательное )
     * @return страница со списком печатей
     */
    @GetMapping("/products/печати")
    public  String findAllStampsByTypeAndSort(
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortType)  {
        List<StampDTO> response  = productService.findAllStampsAndSort(sortField, sortType);
        System.out.println(response);
        return "products.html";
    }

    @GetMapping("/products/визитки")
    public  String findAllBusinessCardsByTypeAndSort(
    @RequestParam(required = false) String sortField,
    @RequestParam(required = false) String sortType)  {
        List<BusinessCardDTO> response  = productService.findAllBusinessCardsAndSort(sortField, sortType);
        System.out.println(response);
        return "products.html";
    }


}
