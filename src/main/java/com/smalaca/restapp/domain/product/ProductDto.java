package com.smalaca.restapp.domain.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ProductDto {
    private Long id;
    private String serialNumber;
    private String name;
    private BigDecimal price;
    private String description;
    private Long shopId;
}
