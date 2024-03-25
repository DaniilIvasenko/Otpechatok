package ru.otpechatok.data;

import lombok.Data;
import lombok.Getter;

/**
 * текущий статус заказа
 */
@Getter
public enum Status {
    TO_DO("в разработке"),
    COMPLETE("выполнен"),
    CANCELED("отменен");

    private String status;


    Status(String status) {
        this.status = status;
    }
}
