package com.lastminute.exceptions;

public class InvalidDepartureDateException extends RuntimeException {
    public InvalidDepartureDateException(String message) {
        super(message);
    }
}
