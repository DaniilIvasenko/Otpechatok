package ru.otpechatok.repository.BusinessCard;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otpechatok.data.businessCard.BusinessCardSize;

public interface BusinessCardSizeRepository extends JpaRepository<BusinessCardSize,Long> {
}
