package com.lastminute.flight.search.exceptions;

public class InvalidDepartureDateException extends RuntimeException {
    public InvalidDepartureDateException(String message) {
        super(message);
    }
}
