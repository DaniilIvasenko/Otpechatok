package ru.otpechatok.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private List<OrderDetailsDTO> orderDetail;
     private  String description;
     private  String price;
     private String timeOfCreation;
     private String status;
}
