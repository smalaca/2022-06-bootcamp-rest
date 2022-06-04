package com.smalaca.restapp.api.rest;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class HelloRestControllerTest {
    @Test
    void shouldSayHelloWhenNameGiven() {
        String url = "http://localhost:8013/hello/sebastian";
        RestTemplate client = new RestTemplate();

        String result = client.getForObject(url, String.class);

        System.out.println(result);
    }

    @Test
    void shouldSayHelloWorldWhenNoNameGiven() {
        String url = "http://localhost:8013/hello";
        RestTemplate client = new RestTemplate();

        String result = client.getForObject(url, String.class);

        System.out.println(result);
    }
}