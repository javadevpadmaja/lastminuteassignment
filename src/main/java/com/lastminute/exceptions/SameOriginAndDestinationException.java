package com.lastminute.exceptions;

public class SameOriginAndDestinationException extends RuntimeException {
    public SameOriginAndDestinationException(String message) {
        super(message);
    }
}
