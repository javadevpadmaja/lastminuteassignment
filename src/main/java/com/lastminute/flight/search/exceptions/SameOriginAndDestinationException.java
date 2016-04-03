package com.lastminute.flight.search.exceptions;

public class SameOriginAndDestinationException extends RuntimeException {
    public SameOriginAndDestinationException(String message) {
        super(message);
    }
}
