package ru.otpechatok.repository.stamp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otpechatok.data.stamp.StampSize;

public interface StampSizeRepository extends JpaRepository<StampSize, Long> {
}
