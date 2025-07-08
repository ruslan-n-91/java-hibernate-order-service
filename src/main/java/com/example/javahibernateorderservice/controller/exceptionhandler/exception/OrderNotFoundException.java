package com.example.javahibernateorderservice.controller.exceptionhandler.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
