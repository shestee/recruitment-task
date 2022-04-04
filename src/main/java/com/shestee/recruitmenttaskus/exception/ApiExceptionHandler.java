package com.shestee.recruitmenttaskus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApiExceptionHandler {
    final String PERSON_NOT_FOUND_MSG = "person not found";
    final String PERSON_EXISTS_MSG = "person already exists";

    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException e) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        ApiException notFoundException = new ApiException(PERSON_NOT_FOUND_MSG, notFoundStatus, ZonedDateTime.now());

        return new ResponseEntity<>(notFoundException, notFoundStatus);
    }

    @ExceptionHandler(value = PersonExistsException.class)
    public ResponseEntity<Object> handlePersonExistsException(PersonExistsException e) {
        HttpStatus personExistsStatus = HttpStatus.CONFLICT;
        ApiException notFoundException = new ApiException(PERSON_EXISTS_MSG, personExistsStatus, ZonedDateTime.now());

        return new ResponseEntity<>(notFoundException, personExistsStatus);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private Error processFieldErrors(List<FieldError> fieldErrors) {
        Error error = new Error(BAD_REQUEST.value(), "validation error");
        for (FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public void addFieldError(String object, String field, String message) {
            FieldError error = new FieldError(object, field, message);
            fieldErrors.add(error);
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }
    }
}
