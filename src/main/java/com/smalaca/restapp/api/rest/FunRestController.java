package com.smalaca.restapp.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("rest")
public class FunRestController {
    @GetMapping("fun")
    public String isFun() {
        return "REST is fun!";
    }

    @GetMapping(value = {"{login}", "{login}/{luckyNumber}"})
    public String luckyNumber(@PathVariable String login, @PathVariable Optional<Integer> luckyNumber) {
        return "Cześć, jestem " + login + ". Moja szczęśliwa liczba to: " + luckyNumber.orElse(13);
    }

    @GetMapping
    public String luckyNumberForNoName() {
        return "Kim jesteś i jaka jest Twoja szczęśliwa liczba?";
    }
}
