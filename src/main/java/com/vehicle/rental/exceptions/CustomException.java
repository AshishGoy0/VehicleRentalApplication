package com.vehicle.rental.exceptions;


public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorCode code;

    public CustomException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

}
