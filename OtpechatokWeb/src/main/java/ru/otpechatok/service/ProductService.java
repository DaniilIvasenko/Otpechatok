package ru.otpechatok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otpechatok.data.stamp.Stamp;
import ru.otpechatok.dto.products.BusinessCardDTO;
import ru.otpechatok.dto.products.ProductDTO;
import ru.otpechatok.dto.products.StampDTO;
import ru.otpechatok.models.ProductApi;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductApi productApi;


    /**
     * получить полный список продуктов
     * @return
     */
    public List<ProductDTO> getAllProducts(){
        return  null;
    }

    /**
     * получить список печатей с возможностью сортировки
     * @param sortField поле по которому необходимо выполнить сортировку
     * @param sortType тип сортировки (по возрастанию / убыванию)
     * @return список продуктов полученный с сервера
     */
    public  List<StampDTO> findAllStampsAndSort(String sortField, String sortType ) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = String.format("%s/stamp%s%s",
                productApi.getUrl(),
                sortField!=null? "?sortField="+sortField:"",
                sortType!=null? "&sortType=":"");

        ResponseEntity<List<StampDTO>> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){},
                Stamp.class);

        return response.getBody();
    }

    public  List<BusinessCardDTO> findAllBusinessCardsAndSort(String sortField, String sortType ) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = String.format("%s/BUSINESS_CARD%s%s",
                productApi.getUrl(),
                sortField!=null? "?sortField="+sortField:"",
                sortType!=null? "&sortType=":"");

        ResponseEntity<List<BusinessCardDTO>> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){},
                Stamp.class);

        return response.getBody();
    }
}
