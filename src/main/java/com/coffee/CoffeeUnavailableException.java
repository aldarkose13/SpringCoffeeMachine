package com.coffee;

public class CoffeeUnavailableException extends RuntimeException {
    public CoffeeUnavailableException(String message) {
        super(message);
    }
}
