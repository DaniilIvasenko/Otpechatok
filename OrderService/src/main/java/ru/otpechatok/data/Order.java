package ru.otpechatok.data;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * сущность заказа
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * id пользователя сделавшего заказ
     */
    private Long userId;
    /**
     * детали заказа (список товаров)
     */
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetail;
    /**
     * описание заказа
     */
    private String description;
    /**
     * суммарная цена заказа
     */
    private BigDecimal price;
    /**
     * время создания заказа
     */
    private LocalDateTime timeOfCreation;
    /**
     * статус выполнения заказа
     */
    private Status status;
}
