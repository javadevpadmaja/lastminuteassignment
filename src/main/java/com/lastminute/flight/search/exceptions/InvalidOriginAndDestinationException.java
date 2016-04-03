package com.lastminute.flight.search.exceptions;

public class InvalidOriginAndDestinationException extends RuntimeException {
    public InvalidOriginAndDestinationException(String message) {
        super(message);
    }
}
