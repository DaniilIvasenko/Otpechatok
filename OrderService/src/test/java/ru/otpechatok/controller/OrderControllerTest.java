package ru.otpechatok.controller;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otpechatok.data.Order;
import ru.otpechatok.dto.OrderDetailsDTO;
import ru.otpechatok.service.iOrderService;


import java.util.List;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderControllerTest {
    @MockBean
    private iOrderService orderService;
    @Autowired
    private OrderController orderController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("orders search")
    void findAllOrdersByUserId() {
        List<Order> orders = List.of(new Order(), new Order());
        given(orderService.findAllOrdersByUserId(1l)).willReturn(orders);
        orderController.findAllOrdersByUserId(1l);
        verify(orderService).findAllOrdersByUserId(1l);
    }

    @Test
    @DisplayName("order creating test")
    void createOrder() {
        List<OrderDetailsDTO> orderDetailsDTOS = List.of(new OrderDetailsDTO(), new OrderDetailsDTO());
        Order responceOrder = new Order();
        given(orderService.createOrder(orderDetailsDTOS, 1l)).willReturn(responceOrder);
        orderController.createOrder(orderDetailsDTOS, 1l);
        verify(orderService).createOrder(orderDetailsDTOS, 1l);

    }

    @Test
    @DisplayName("Order canceling test")
    void cancelOrderById() {
        orderController.cancelOrderById(1l);
        verify(orderService).cancelOrderById(1l);
    }
}