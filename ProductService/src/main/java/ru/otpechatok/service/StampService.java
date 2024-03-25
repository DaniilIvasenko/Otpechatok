package ru.otpechatok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otpechatok.data.stamp.Stamp;
import ru.otpechatok.data.stamp.StampBodyType;
import ru.otpechatok.data.stamp.StampSize;
import ru.otpechatok.repository.stamp.StampBodyTypeRepository;
import ru.otpechatok.repository.stamp.StampRepository;
import ru.otpechatok.repository.stamp.StampSizeRepository;

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

    @Override
    public List<Stamp> findAll() {
        return stampRepository.findAll();
    }


    @Override
    public List<Stamp> findAllOrderByFieldASC(String fieldName) {
        return stampRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
    }


    @Override
    public List<Stamp> findAllOrderByFieldDESC(String fieldName) {
        return stampRepository.findAll(Sort.by(Sort.Direction.DESC, fieldName));
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
