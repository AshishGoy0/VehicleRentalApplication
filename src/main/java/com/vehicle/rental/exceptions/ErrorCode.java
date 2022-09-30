package com.vehicle.rental.exceptions;

public enum ErrorCode {

    UNKNOWN("UNKNOWN"),
    INVALID("INVALID"),
    NOT_FOUND("NOT_FOUND"),
    BAD_REQUEST("BAD_REQUEST"),
    UNAUTHORIZED("UNAUTHORIZED"),
    FORBIDDEN("FORBIDDEN"),
    UNPROCESSABLE_ENTITY("UNPROCESSABLE_ENTITY"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    DOWN_STREAM_ERROR("DOWN_STREAM_ERROR"),
    CONFLICT("CONFLICT"),
    IMPLEMENTATION_NOT_FOUND("IMPLEMENTATION_NOT_FOUND");

    private String message;

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
