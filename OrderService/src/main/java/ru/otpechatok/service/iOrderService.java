package ru.otpechatok.service;

import ru.otpechatok.data.Order;
import ru.otpechatok.data.OrderDetails;

import java.util.List;

public interface iOrderService {
    /**
     * найти все заказы пользователя
     * @param userId id пользователя
     * @return список заказов пользователя
     */
    List<Order>  findAllOrdersByUserId(Long userId);


    /**
     * создать новый заказ
     * @param orderDetails детали заказа
     * @param userId id пользователя сделавшего заказ
     * @return заказ из после сохранения с присвоенными id
     */
    Order createOrder(List<OrderDetails> orderDetails, Long userId);


    /**
     * изменение статуса заказа на CANCELED
     * @param orderId id заказа
     */
    void  cancelOrderById(Long orderId);
}
