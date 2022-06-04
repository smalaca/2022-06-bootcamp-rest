package com.smalaca.restapp.api.rest.product;

import com.smalaca.restapp.domain.product.Product;
import com.smalaca.restapp.domain.product.ProductDto;
import com.smalaca.restapp.domain.product.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("product")
public class ProductRestController {
    private final ProductRepository repository;

    public ProductRestController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(Product::asDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Long create(@RequestBody ProductDto dto) {
        Product product = new Product(dto.getSerialNumber(), dto.getName(), dto.getPrice(), dto.getDescription(), dto.getShopId());

        return repository.save(product).getId();
    }
}
