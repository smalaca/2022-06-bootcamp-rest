package com.smalaca.restapp.api.rest.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ProductDto {
    private final Long id;
    private final String serialNumber;
    private final String name;
    private final BigDecimal price;
    private final String description;
    private final Long shopId;
}
