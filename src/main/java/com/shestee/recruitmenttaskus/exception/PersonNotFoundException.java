package com.shestee.recruitmenttaskus.exception;

public class PersonNotFoundException extends RuntimeException {
    private String message;

    public PersonNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public PersonNotFoundException() {
    }
}
