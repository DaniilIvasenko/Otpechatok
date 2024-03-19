package ru.otpechatok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpechatok.data.businessCard.BusinessCard;
import ru.otpechatok.data.businessCard.BusinessCardSize;
import ru.otpechatok.data.businessCard.MaterialType;
import ru.otpechatok.repository.BusinessCard.BusinessCardMaterialTypeRepository;
import ru.otpechatok.repository.BusinessCard.BusinessCardRepository;
import ru.otpechatok.repository.BusinessCard.BusinessCardSizeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessCardService implements iProductService<BusinessCard> {
    private  final BusinessCardMaterialTypeRepository businessCardMaterialTypeRepository;
    private  final BusinessCardSizeRepository businessCardSizeRepository;
    private  final BusinessCardRepository businessCardRepository;

    @Override
    public List<BusinessCard> findAll() {
        return businessCardRepository.findAll();
    }

    public BusinessCard add(BusinessCard businessCard){
        BusinessCard businessCardFromDb = businessCardRepository.save(businessCard);

        List<BusinessCardSize> businessCardSizes = businessCardFromDb.getSizes();
        if (businessCardSizes!=null){
            businessCardSizes.stream().forEach(x->x.setBusinessCard(businessCardFromDb));
            businessCardSizeRepository.saveAll(businessCardSizes);
        }

        List<MaterialType> materialTypes = businessCardFromDb.getMaterialTypes();
        if (materialTypes!= null){
            materialTypes.stream().forEach(x->x.setBusinessCard(businessCardFromDb));
            businessCardMaterialTypeRepository.saveAll(materialTypes);
        }
        return businessCardFromDb;
    }


}
