package ru.otpechatok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otpechatok.data.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
