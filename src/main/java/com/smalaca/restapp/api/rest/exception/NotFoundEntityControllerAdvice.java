package com.smalaca.restapp.api.rest.exception;

import com.smalaca.restapp.api.rest.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundEntityControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<String> handle(NotFoundEntityException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
