package com.smalaca.restapp.domain.product;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findAllBySerialNumberOrName(String serialNumber, String name);

    Iterable<Product> findAllByShopIdIn(List<Long> shopIds);
}
