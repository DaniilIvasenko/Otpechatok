package ru.otpechatok.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.otpechatok.data.Order;
import ru.otpechatok.data.OrderDetails;
import ru.otpechatok.data.Status;
import ru.otpechatok.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OderService implements iOrderService{
    private  final OrderRepository orderRepository;
    @Override
    public List<Order> findAllOrdersByUserId(Long userId) {

        return  orderRepository.findAllByUserId(userId);
    }

    @Override
    public Order createOrder(List<OrderDetails> orderDetails, Long userId) {
        Order orderToSave = new Order();
        orderToSave.setOrderDetail(orderDetails);
        orderToSave.setUserId(userId);
        orderToSave.setTimeOfCreation(LocalDateTime.now());
        BigDecimal price= new BigDecimal(0);
        orderDetails.stream().forEach(x->price.add(x.getPriceForOne().multiply(BigDecimal.valueOf(userId))));
        orderToSave.setStatus(Status.TO_DO);
        return orderRepository.save(orderToSave);
    }

    @Override
    public void cancelOrderById(Long orderId) {

    }
}
