package ru.otpechatok.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otpechatok.data.Order;
import ru.otpechatok.data.OrderDetails;
import ru.otpechatok.data.Status;
import ru.otpechatok.repository.OrderDetailsRepository;
import ru.otpechatok.repository.OrderRepository;


import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class OderServiceTest {
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private  OderService orderService;
    private Order order;
    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1l);
        order.setUserId(1l);
        order.setDescription("test Order1");
        List<OrderDetails> orderDetailsList = List.of(new OrderDetails(), new OrderDetails());
        order.setOrderDetail(orderDetailsList);
    }

    @AfterEach
    void tearDown() {
        order =  null;
    }

    @Test
    @DisplayName("orders search by userID test")
    void findAllOrdersByUserId() {
        Order order2 = new Order();
        order2.setUserId(1l);
        order2.setPrice(new BigDecimal(20));
        order2.setDescription("test Order2");
        order2.setOrderDetail(List.of(new OrderDetails()));

        List<Order> orders = List.of(order, order2);

        given(orderRepository.findAllByUserId(order.getUserId())).willReturn(orders);

        orderService.findAllOrdersByUserId(order.getUserId());

        verify(orderRepository).findAllByUserId(1l);
    }


    @Test
    @DisplayName("order cancel test")
    void cancelOrderById() {
        given(orderRepository.findById(order.getId())).willReturn(Optional.of(order));
        order.setStatus(Status.CANCELED);
        given(orderRepository.save(order)).willReturn(order);
        orderService.cancelOrderById(order.getId());
        verify(orderRepository).findById(order.getId());
        verify(orderRepository).save(order);
    }
}