package ru.otpechatok.service;

import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import mappers.BusinessCardToDtoMapper;
import mappers.iProductMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otpechatok.data.businessCard.BusinessCard;
import ru.otpechatok.data.businessCard.BusinessCardSize;
import ru.otpechatok.data.businessCard.MaterialType;
import ru.otpechatok.repository.BusinessCard.BusinessCardMaterialTypeRepository;
import ru.otpechatok.repository.BusinessCard.BusinessCardRepository;
import ru.otpechatok.repository.BusinessCard.BusinessCardSizeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * сервис работающий с визитками
 */
@Service
@RequiredArgsConstructor
public class BusinessCardService implements iProductService<BusinessCard> {
    private final BusinessCardMaterialTypeRepository businessCardMaterialTypeRepository;
    private final BusinessCardSizeRepository businessCardSizeRepository;
    private final BusinessCardRepository businessCardRepository;
    //todo сделать внедрение через spring
    private  final iProductMapper productMapper = new BusinessCardToDtoMapper();

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        businessCardRepository.findAll().stream()
                .forEach(x->productDTOS.add(productMapper.formEntityToDTO(x)));

        return productDTOS;
    }

    @Override
    public List<ProductDTO> findAllOrderByFieldASC(String fieldName) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        businessCardRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName)).stream()
                .forEach(x->productDTOS.add(productMapper.formEntityToDTO(x)));

        return productDTOS;
    }


    @Override
    public List<ProductDTO> findAllOrderByFieldDESC(String fieldName) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        businessCardRepository.findAll(Sort.by(Sort.Direction.DESC, fieldName)).stream()
                .forEach(x->productDTOS.add(productMapper.formEntityToDTO(x)));

        return productDTOS;
    }


    public BusinessCard add(BusinessCard businessCard) {
        BusinessCard businessCardFromDb = businessCardRepository.save(businessCard);

        List<BusinessCardSize> businessCardSizes = businessCardFromDb.getSizes();
        if (businessCardSizes != null) {
            businessCardSizes.stream().forEach(x -> x.setBusinessCard(businessCardFromDb));
            businessCardSizeRepository.saveAll(businessCardSizes);
        }

        List<MaterialType> materialTypes = businessCardFromDb.getMaterialTypes();
        if (materialTypes != null) {
            materialTypes.stream().forEach(x -> x.setBusinessCard(businessCardFromDb));
            businessCardMaterialTypeRepository.saveAll(materialTypes);
        }
        return businessCardFromDb;
    }
}
