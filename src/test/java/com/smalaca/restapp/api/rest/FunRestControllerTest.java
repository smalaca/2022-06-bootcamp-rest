package com.smalaca.restapp.api.rest;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class FunRestControllerTest {
    private static final String URL = "http://localhost:8123/rest";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldVerifyIfRestIsFun() {
        String result = client.getForObject(URL + "/fun", String.class);

        System.out.println(result);
    }

    @Test
    void shouldRecognizeWhatHappensWhenNoLoginAndNoLuckyNumberGiven() {
        String result = client.getForObject(URL, String.class);

        System.out.println(result);
    }

    @Test
    void shouldRecognizeWhatHappensWhenLoginAndNoLuckyNumberGiven() {
        String result = client.getForObject(URL + "/steverogers/42", String.class);

        System.out.println(result);
    }

    @Test
    void shouldRecognizeWhatHappensWhenLoginAndLuckyNumberGiven() {
        String result = client.getForObject(URL + "/scarletwitch", String.class);

        System.out.println(result);
    }
}