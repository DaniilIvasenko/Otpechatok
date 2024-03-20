package ru.otpechatok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otpechatok.data.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
