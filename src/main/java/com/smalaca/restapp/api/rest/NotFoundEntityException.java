package com.smalaca.restapp.api.rest;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(Long id) {
        super("Entity with id: " + id + " does not exist.");
    }
}
