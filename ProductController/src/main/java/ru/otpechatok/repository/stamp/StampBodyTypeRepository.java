package ru.otpechatok.repository.stamp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otpechatok.data.stamp.StampBodyType;

public interface StampBodyTypeRepository extends JpaRepository<StampBodyType, Long> {
}
