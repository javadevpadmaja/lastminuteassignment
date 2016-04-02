package com.lastminute.exceptions;

public class InvalidOriginAndDestinationException extends RuntimeException {
    public InvalidOriginAndDestinationException(String message) {
        super(message);
    }
}
