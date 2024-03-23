package ru.otpechatok.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.otpechatok.data.Order;
import ru.otpechatok.data.OrderDetails;
import ru.otpechatok.data.Status;
import ru.otpechatok.dto.OrderDetailsDTO;
import ru.otpechatok.repository.OrderDetailsRepository;
import ru.otpechatok.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OderService implements iOrderService{
    private  final OrderRepository orderRepository;
    private  final OrderDetailsRepository orderDetailsRepository;
    @Override
    public List<Order> findAllOrdersByUserId(Long userId) {

        return  orderRepository.findAllByUserId(userId);
    }


    @Override
    public Order createOrder(List<OrderDetailsDTO> orderDetailsDTOS, Long userId) {
        Order orderToSave = new Order();
        orderToSave.setUserId(userId);
        orderToSave.setTimeOfCreation(LocalDateTime.now());
        BigDecimal price= new BigDecimal(0);
        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
            BigDecimal sumPrice = BigDecimal.valueOf(orderDetailsDTO.getPriceForOne())
                    .multiply(BigDecimal.valueOf(orderDetailsDTO.getAmount()));
            price = price.add(sumPrice);
        }

        orderToSave.setPrice(price);
        orderToSave.setStatus(Status.TO_DO);
        orderToSave = orderRepository.save(orderToSave);

        Order finalOrderToSave = orderToSave;
        List<OrderDetails> orderDetails = orderDetailsDTOS.stream()
                .map(x->{
                    OrderDetails result  = OrderDetails.convertFromOrderDetailsDTO(x);
                    result.setOrder(finalOrderToSave);
                    return result;
                })
                .collect(Collectors.toList());
        List<OrderDetails> detailsFromDB = orderDetailsRepository.saveAll(orderDetails);
        finalOrderToSave.setOrderDetail(detailsFromDB);
        return finalOrderToSave;
    }

    @Override
    public void cancelOrderById(Long orderId) {
        Order orderFromDB =  orderRepository.findById(orderId).get();
        orderFromDB.setStatus(Status.CANCELED);
        orderRepository.save(orderFromDB);
    }
}
