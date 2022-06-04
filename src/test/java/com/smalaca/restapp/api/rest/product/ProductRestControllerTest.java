package com.smalaca.restapp.api.rest.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

class ProductRestControllerTest {
    private static final String URL = "http://localhost:8123/product";
    private static final RestTemplate CLIENT = new RestTemplate();

    @BeforeAll
    static void beforeAll() {
        CLIENT.postForObject(URL, new ProductTestDto("AFA141", "Water", BigDecimal.valueOf(13), "to drink", 1L), Long.class);
        CLIENT.postForObject(URL, new ProductTestDto("AAA123", "Water", BigDecimal.valueOf(42), "to drink", 1L), Long.class);
        CLIENT.postForObject(URL, new ProductTestDto("CZX123", "Water", BigDecimal.valueOf(55), "to drink", 3L), Long.class);
        CLIENT.postForObject(URL, new ProductTestDto("FDS134", "Bread", BigDecimal.valueOf(42), "to eat", 1L), Long.class);
        CLIENT.postForObject(URL, new ProductTestDto("FDS135", "Coffee", BigDecimal.valueOf(42), "to eat", 3L), Long.class);
        CLIENT.postForObject(URL, new ProductTestDto("KJH855", "Coffee", BigDecimal.valueOf(12.34), "the best drink ever", 2L), Long.class);
        CLIENT.postForObject(URL, new ProductTestDto("KJJ577", "Tea", BigDecimal.valueOf(7.13), "good to drink from time to time", 3L), Long.class);
    }

    @Test
    void shouldReturnProductsForGivenNameOrSerialNumber() {
        ProductTestDto[] response = CLIENT.getForObject(URL + "?serialNumber=FDS134&name=Water", ProductTestDto[].class);

        Arrays.stream(response).forEach(System.out::println);
    }

    @Test
    void shouldReturnProductsForGivenShops() {
        ProductTestDto[] response = CLIENT.getForObject(URL + "?shopIds=2,3", ProductTestDto[].class);

        Arrays.stream(response).forEach(System.out::println);
    }

    @Test
    void shouldReturnProducts() {
        ProductTestDto[] response = CLIENT.getForObject(URL, ProductTestDto[].class);

        Arrays.stream(response).forEach(System.out::println);
    }

    @Test
    void shouldReturnSpecificProduct() {
        Long id = CLIENT.postForObject(URL, new ProductTestDto("XYZ987", "Watermelon", BigDecimal.valueOf(42.13), "to eat", 13L), Long.class);

        ProductTestDto response = CLIENT.getForObject(URL + "/" + id, ProductTestDto.class);

        System.out.println(response);
    }

    @Test
    void shouldUpdateSpecificProduct() {
        Long id = CLIENT.postForObject(URL, new ProductTestDto("IUD456", "Honey", BigDecimal.valueOf(100), "sweet", 1L), Long.class);

        CLIENT.put(URL + "/" + id, new ProductTestDto("Sweet Honey", BigDecimal.valueOf(97), "great for breakfest"));

        ProductTestDto response = CLIENT.getForObject(URL + "/" + id, ProductTestDto.class);
        System.out.println(response);
    }

    @Test
    void shouldDeleteSpecificProduct() {
        CLIENT.postForObject(URL, new ProductTestDto("YYY141", "Water", BigDecimal.valueOf(13), "to drink", 12L), Long.class);
        CLIENT.postForObject(URL, new ProductTestDto("ABC134", "Bread", BigDecimal.valueOf(42), "to eat", 12L), Long.class);
        Long id = CLIENT.postForObject(URL, new ProductTestDto("ZZZ855", "Coffee", BigDecimal.valueOf(12.34), "the best drink ever", 22L), Long.class);

        CLIENT.delete(URL + "/" + id);

        ProductTestDto[] response = CLIENT.getForObject(URL, ProductTestDto[].class);
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

        ProductTestDto(String name, BigDecimal price, String description) {
            this.name = name;
            this.price = price;
            this.description = description;
        }
    }
}