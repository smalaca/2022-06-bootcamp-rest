package com.smalaca.restapp.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class FunRestController {
    @GetMapping
    public String isFun() {
        return "REST is fun!";
    }

    @GetMapping("/{login}/{luckyNumber}")
    public String luckyNumber(@PathVariable String login, @PathVariable int luckyNumber) {
        return "Cześć, jestem " + login + ". Moja szczęśliwa liczba to: " + luckyNumber;
    }
}
