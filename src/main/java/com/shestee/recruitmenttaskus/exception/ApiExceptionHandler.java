package com.shestee.recruitmenttaskus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {PersonNotFoundException.class})
    public ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException notFoundException = new ApiException(e.getMessage(), notFound, ZonedDateTime.now());

        return new ResponseEntity<>(notFoundException, notFound);
    }
}
