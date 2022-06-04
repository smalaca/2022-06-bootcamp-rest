package com.smalaca.restapp.api.rest.product;

import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductRestController {
    @GetMapping
    public List<ProductDto> findAll() {
        return ImmutableList.of(
               new ProductDto(1L, "AFA141", "Water", BigDecimal.valueOf(13), "to drink", 1L),
               new ProductDto(2L, "FDS134", "Bread", BigDecimal.valueOf(42), "to eat", 1L),
               new ProductDto(3L, "KJH855", "Coffee", BigDecimal.valueOf(12.34), "the best drink ever", 2L),
               new ProductDto(4L, "KJJ577", "Tea", BigDecimal.valueOf(7.13), "good to drink from time to time", 3L)
        );
    }
}
