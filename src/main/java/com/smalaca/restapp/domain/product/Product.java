package com.smalaca.restapp.domain.product;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String creationHost;
    private String creationUser;
    private String serialNumber;
    private String name;
    private BigDecimal price;
    private String description;
    private Long shopId;

    public Product(String creationHost, String creationUser, String serialNumber, String name, BigDecimal price, String description, Long shopId) {
        this.creationHost = creationHost;
        this.creationUser = creationUser;
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.description = description;
        this.shopId = shopId;
    }

    public ProductDto asDto() {
        return new ProductDto(id, creationHost, creationUser, serialNumber, name, price, description, shopId);
    }

    public Long getId() {
        return id;
    }

    public void update(ProductDto dto) {
        name = dto.getName();
        price = dto.getPrice();
        description = dto.getDescription();
    }
}
