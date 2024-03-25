package ru.otpechatok.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import ru.otpechatok.data.stamp.Stamp;
import ru.otpechatok.data.stamp.StampBodyType;
import ru.otpechatok.data.stamp.StampSize;
import ru.otpechatok.repository.stamp.StampBodyTypeRepository;
import ru.otpechatok.repository.stamp.StampRepository;
import ru.otpechatok.repository.stamp.StampSizeRepository;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class StampServiceTest {
    @MockBean
    private StampRepository stampRepository;
    @MockBean
    private StampSizeRepository stampSizeRepository;
    @MockBean
    private StampBodyTypeRepository stampBodyTypeRepository;

    @Autowired
    private StampService stampService;

    @Test
    @DisplayName("all searching test")
    void findAll() {
        List<Stamp> stamps = List.of(new Stamp(), new Stamp());
        given(stampRepository.findAll()).willReturn(stamps);
        stampService.findAll();
        verify(stampRepository).findAll();
    }


    @Test
    @DisplayName("stamp searching and ASC sorting test")
    void findAllOrderByFieldASC() {
        List<Stamp> stamps = List.of(new Stamp(), new Stamp());
        given(stampRepository.findAll(Sort.by(Sort.Direction.ASC, "price"))).willReturn(stamps);
        stampService.findAllOrderByFieldASC("price");
        verify(stampRepository).findAll(Sort.by(Sort.Direction.ASC, "price"));
    }


    @Test
    @DisplayName("stamp searching and DESC sorting test")
    void findAllOrderByFieldDESC() {
        List<Stamp> stamps = List.of(new Stamp(), new Stamp());
        given(stampRepository.findAll(Sort.by(Sort.Direction.DESC, "price"))).willReturn(stamps);
        stampService.findAllOrderByFieldDESC("price");
        verify(stampRepository).findAll(Sort.by(Sort.Direction.DESC, "price"));
    }


    @Test
    @DisplayName("stamp save testing")
    void add() {
        Stamp stamp = new Stamp();

        StampBodyType stampBodyType1 = new StampBodyType();
        stampBodyType1.setStamp(stamp);
        StampBodyType stampBodyType2 = new StampBodyType();
        stampBodyType2.setStamp(stamp);

        stamp.setBodyTypes(List.of(stampBodyType1, stampBodyType2));

        StampSize stampSize1 = new StampSize();
        stampSize1.setStamp(stamp);
        StampSize stampSize2 = new StampSize();
        stampSize2.setStamp(stamp);

        stamp.setSizes(List.of(stampSize1, stampSize2));

        given(stampRepository.save(stamp)).willReturn(stamp);
        given(stampBodyTypeRepository.saveAll(stamp.getBodyTypes())).willReturn(stamp.getBodyTypes());
        given(stampSizeRepository.saveAll(stamp.getSizes())).willReturn(stamp.getSizes());
        stampService.add(stamp);
        verify(stampRepository).save(stamp);
        verify(stampBodyTypeRepository).saveAll(stamp.getBodyTypes());
        verify(stampSizeRepository).saveAll(stamp.getSizes());


    }
}