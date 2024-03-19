package ru.otpechatok.repository.BusinessCard;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otpechatok.data.businessCard.BusinessCard;

public interface BusinessCardRepository extends JpaRepository<BusinessCard, Long> {
}
