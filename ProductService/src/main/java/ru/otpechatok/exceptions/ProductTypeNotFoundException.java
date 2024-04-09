package ru.otpechatok.exceptions;

import java.util.function.Supplier;

public class ProductTypeNotFoundException extends RuntimeException {
    public ProductTypeNotFoundException(String message) {
        super(message);
    }

    public ProductTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public ProductTypeNotFoundException() {

    }
}
