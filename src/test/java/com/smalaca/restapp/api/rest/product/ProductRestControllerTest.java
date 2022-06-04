package com.smalaca.restapp.api.rest.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

class ProductRestControllerTest {
    private static final String URL = "http://localhost:8123/product";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnProducts() {
        client.postForObject(URL, new ProductTestDto("AFA141", "Water", BigDecimal.valueOf(13), "to drink", 1L), Long.class);
        client.postForObject(URL, new ProductTestDto("FDS134", "Bread", BigDecimal.valueOf(42), "to eat", 1L), Long.class);
        client.postForObject(URL, new ProductTestDto("KJH855", "Coffee", BigDecimal.valueOf(12.34), "the best drink ever", 2L), Long.class);
        client.postForObject(URL, new ProductTestDto("KJJ577", "Tea", BigDecimal.valueOf(7.13), "good to drink from time to time", 3L), Long.class);

        ProductTestDto[] response = client.getForObject(URL, ProductTestDto[].class);

        Arrays.stream(response).forEach(System.out::println);
    }

    @Test
    void shouldReturnSpecificProduct() {
        Long id = client.postForObject(URL, new ProductTestDto("XYZ987", "Watermelon", BigDecimal.valueOf(42.13), "to eat", 13L), Long.class);

        ProductTestDto response = client.getForObject(URL + "/" + id, ProductTestDto.class);

        System.out.println(response);
    }

    @Test
    void shouldDeleteSpecificProduct() {
        client.postForObject(URL, new ProductTestDto("YYY141", "Water", BigDecimal.valueOf(13), "to drink", 12L), Long.class);
        client.postForObject(URL, new ProductTestDto("ABC134", "Bread", BigDecimal.valueOf(42), "to eat", 12L), Long.class);
        Long id = client.postForObject(URL, new ProductTestDto("ZZZ855", "Coffee", BigDecimal.valueOf(12.34), "the best drink ever", 22L), Long.class);

        client.delete(URL + "/" + id);

        ProductTestDto[] response = client.getForObject(URL, ProductTestDto[].class);
        Arrays.stream(response).forEach(System.out::println);
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class ProductTestDto {
        private Long id;
        private String serialNumber;
        private String name;
        private BigDecimal price;
        private String description;
        private Long shopId;

        ProductTestDto(String serialNumber, String name, BigDecimal price, String description, Long shopId) {
            this.serialNumber = serialNumber;
            this.name = name;
            this.price = price;
            this.description = description;
            this.shopId = shopId;
        }
    }
}