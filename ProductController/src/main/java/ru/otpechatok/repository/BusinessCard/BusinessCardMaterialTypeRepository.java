package ru.otpechatok.repository.BusinessCard;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otpechatok.data.businessCard.MaterialType;

public interface BusinessCardMaterialTypeRepository extends JpaRepository<MaterialType, Long> {
}
