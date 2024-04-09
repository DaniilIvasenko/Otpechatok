package ru.otpechatok.service;

import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import mappers.StampToStampDtoMapper;
import mappers.iProductMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otpechatok.data.stamp.Stamp;
import ru.otpechatok.data.stamp.StampBodyType;
import ru.otpechatok.data.stamp.StampSize;
import ru.otpechatok.repository.stamp.StampBodyTypeRepository;
import ru.otpechatok.repository.stamp.StampRepository;
import ru.otpechatok.repository.stamp.StampSizeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * сервис работающий с печатями
 */
@Service
@RequiredArgsConstructor
public class StampService implements iProductService<Stamp> {
    private final StampRepository stampRepository;
    private final StampSizeRepository stampSizeRepository;
    private final StampBodyTypeRepository stampBodyTypeRepository;
    //todo сделать внедрение через spring
    private final iProductMapper productMapper =  new StampToStampDtoMapper();

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS =  new ArrayList<>();
        stampRepository.findAll().stream()
                .forEach(x->productDTOS.add(productMapper.formEntityToDTO(x)));
        return productDTOS;
    }


    @Override
    public List<ProductDTO> findAllOrderByFieldASC(String fieldName) {
        List<ProductDTO> productDTOS =  new ArrayList<>();
        stampRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName)).stream()
                .forEach(x->productDTOS.add(productMapper.formEntityToDTO(x)));
        return productDTOS;
    }


    @Override
    public List<ProductDTO> findAllOrderByFieldDESC(String fieldName) {
        List<ProductDTO> productDTOS =  new ArrayList<>();
        stampRepository.findAll(Sort.by(Sort.Direction.DESC, fieldName)).stream()
                .forEach(x->productDTOS.add(productMapper.formEntityToDTO(x)));
        return productDTOS;
    }


    @Override
    public Stamp add(Stamp stamp) {
        Stamp stampFromDb = stampRepository.save(stamp);
        List<StampBodyType> stampBodyTypes = stampFromDb.getBodyTypes();
        if (stampBodyTypes != null) {
            stampBodyTypes.stream().forEach(x -> x.setStamp(stampFromDb));
            stampBodyTypeRepository.saveAll(stampBodyTypes);
        }

        List<StampSize> stampSizes = stampFromDb.getSizes();
        if (stampSizes != null) {
            stampSizes.stream().forEach(x -> x.setStamp(stampFromDb));
            stampSizeRepository.saveAll(stampSizes);
        }
        return stampFromDb;
    }
}
