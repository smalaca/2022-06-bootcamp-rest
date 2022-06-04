package com.smalaca.restapp.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(Long id) {
        super("Entity with id: " + id + " does not exist.");
    }
}
