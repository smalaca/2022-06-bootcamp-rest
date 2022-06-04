package com.smalaca.restapp.api.rest.product;

import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductRestControllerTest {
    private static final String URL = "http://localhost:8123/product";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnProducts() {
        ProductTestDto[] response = client.getForObject(URL, ProductTestDto[].class);

        Arrays.stream(response).forEach(System.out::println);
    }

    @Getter
    @ToString
    public static class ProductTestDto {
        private Long id;
        private String serialNumber;
        private String name;
        private BigDecimal price;
        private String description;
        private Long shopId;
    }
}