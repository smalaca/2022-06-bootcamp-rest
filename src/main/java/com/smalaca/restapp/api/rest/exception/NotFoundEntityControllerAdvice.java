package com.smalaca.restapp.api.rest.exception;

import com.smalaca.restapp.api.rest.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotFoundEntityControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle(NotFoundEntityException exception) {
        return exception.getMessage();
    }
}
