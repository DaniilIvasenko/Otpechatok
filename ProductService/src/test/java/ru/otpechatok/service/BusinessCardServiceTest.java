package ru.otpechatok.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import ru.otpechatok.data.businessCard.BusinessCard;
import ru.otpechatok.data.businessCard.BusinessCardSize;
import ru.otpechatok.data.businessCard.MaterialType;
import ru.otpechatok.data.stamp.Stamp;
import ru.otpechatok.data.stamp.StampBodyType;
import ru.otpechatok.data.stamp.StampSize;
import ru.otpechatok.repository.BusinessCard.BusinessCardMaterialTypeRepository;
import ru.otpechatok.repository.BusinessCard.BusinessCardRepository;
import ru.otpechatok.repository.BusinessCard.BusinessCardSizeRepository;
import ru.otpechatok.repository.stamp.StampBodyTypeRepository;
import ru.otpechatok.repository.stamp.StampRepository;
import ru.otpechatok.repository.stamp.StampSizeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BusinessCardServiceTest {
    @MockBean
    private BusinessCardMaterialTypeRepository businessCardMaterialTypeRepository;
    @MockBean
    private BusinessCardSizeRepository businessCardSizeRepository;
    @MockBean
    private BusinessCardRepository businessCardRepository;
    @Autowired
    BusinessCardService businessCardService;


    @Test
    @DisplayName("all searching test")
    void findAll() {
        List<BusinessCard> businessCards = List.of(new BusinessCard(), new BusinessCard());
        given(businessCardRepository.findAll()).willReturn(businessCards);
        businessCardService.findAll();
        verify(businessCardRepository).findAll();
    }

    @Test
    @DisplayName("BusinessCard searching and ASC sorting test")
    void findAllOrderByFieldASC() {
        List<BusinessCard> businessCards = List.of(new BusinessCard(), new BusinessCard());
        given(businessCardRepository.findAll(Sort.by(Sort.Direction.ASC, "price"))).willReturn(businessCards);
        businessCardService.findAllOrderByFieldASC("price");
        verify(businessCardRepository).findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    @Test
    @DisplayName("BusinessCard searching and DESC sorting test")
    void findAllOrderByFieldDESC() {
        List<BusinessCard> businessCards = List.of(new BusinessCard(), new BusinessCard());
        given(businessCardRepository.findAll(Sort.by(Sort.Direction.DESC, "price"))).willReturn(businessCards);
        businessCardService.findAllOrderByFieldDESC("price");
        verify(businessCardRepository).findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    @Test
    @DisplayName("BusinessCard save testing")
    void add() {
        BusinessCard businessCard = new BusinessCard();

        MaterialType materialType1 = new MaterialType();
        materialType1.setBusinessCard(businessCard);
        MaterialType materialType2 = new MaterialType();
        materialType2.setBusinessCard(businessCard);


        businessCard.setMaterialTypes(List.of(materialType1, materialType2));

        BusinessCardSize businessCardSize1 = new BusinessCardSize();
        businessCardSize1.setBusinessCard(businessCard);
        BusinessCardSize businessCardSize2 = new BusinessCardSize();
        businessCardSize2.setBusinessCard(businessCard);


        businessCard.setSizes(List.of(businessCardSize1, businessCardSize2));

        given(businessCardRepository.save(businessCard)).willReturn(businessCard);
        given(businessCardMaterialTypeRepository.saveAll(businessCard.getMaterialTypes())).willReturn(businessCard.getMaterialTypes());
        given(businessCardSizeRepository.saveAll(businessCard.getSizes())).willReturn(businessCard.getSizes());
        businessCardService.add(businessCard);
        verify(businessCardRepository).save(businessCard);
        verify(businessCardMaterialTypeRepository).saveAll(businessCard.getMaterialTypes());
        verify(businessCardSizeRepository).saveAll(businessCard.getSizes());


    }
}