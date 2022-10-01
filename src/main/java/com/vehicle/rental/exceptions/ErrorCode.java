package com.vehicle.rental.exceptions;

public enum ErrorCode {

    UNKNOWN("UNKNOWN"),
    INVALID("INVALID"),
    NOT_FOUND("NOT_FOUND"),
    BAD_REQUEST("BAD_REQUEST");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return this.message;
    }
}
