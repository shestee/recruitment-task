package com.shestee.recruitmenttaskus.exception;

public class PersonExistsException extends RuntimeException {
    private String message;

    public PersonExistsException(String message) {
        super(message);
        this.message = message;
    }

    public PersonExistsException() {
    }
}
