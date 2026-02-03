package com.wessiq.inventoy_management_system.exceptions;

public class ValueRequiredException extends RuntimeException {

    public ValueRequiredException(String message) {
        super(message);
    }
}