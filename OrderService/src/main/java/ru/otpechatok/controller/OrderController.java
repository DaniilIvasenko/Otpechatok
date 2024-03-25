package ru.otpechatok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otpechatok.data.Order;
import ru.otpechatok.data.OrderDetails;
import ru.otpechatok.dto.OrderDetailsDTO;
import ru.otpechatok.service.iOrderService;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final iOrderService orderService;


    /**
     * получить все заказы пользователя
     *
     * @param id id пользователя
     * @return список заказов
     */
    @GetMapping("all_orders")
    public ResponseEntity<List<Order>> findAllOrdersByUserId(@RequestParam Long id) {
        List<Order> orders = orderService.findAllOrdersByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    /**
     * создать новый заказ
     *
     * @param orderDetailsDTOS детали заказа
     * @param id               id пользователя
     * @return ответ об успешном создании заказа
     */
    @PostMapping("create")
    public ResponseEntity<Void> createOrder(@RequestBody List<OrderDetailsDTO> orderDetailsDTOS, @RequestParam Long id) {
        orderService.createOrder(orderDetailsDTOS, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * отмена заказа (изменить статус заказа на CANCELED)
     * @param orderId id отмененного заказа
     */
    @PostMapping("cancel/{orderId}")
    public void cancelOrderById(@PathVariable Long orderId) {
        orderService.cancelOrderById(orderId);
    }
}
