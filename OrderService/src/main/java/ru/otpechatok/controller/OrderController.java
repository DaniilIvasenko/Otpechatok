package ru.otpechatok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otpechatok.data.Order;
import ru.otpechatok.data.OrderDetails;
import ru.otpechatok.service.iOrderService;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private  final iOrderService orderService;
    @GetMapping("all_orders")
    public ResponseEntity<List<Order>> findAllOrdersByUserId(@RequestParam Long id) {
        List<Order> orders = orderService.findAllOrdersByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Void>  createOrder(@RequestBody List<OrderDetails> orderDetails, @RequestParam Long id) {
        orderService.createOrder(orderDetails, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public void cancelOrderById(Long orderId) {

    }
}
