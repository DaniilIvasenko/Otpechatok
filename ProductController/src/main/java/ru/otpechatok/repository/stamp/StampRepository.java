package ru.otpechatok.repository.stamp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otpechatok.data.stamp.Stamp;

public interface StampRepository extends JpaRepository<Stamp, Long> {
}
