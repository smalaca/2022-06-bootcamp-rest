package com.smalaca.restapp.api.rest.product;

import com.smalaca.restapp.domain.product.Product;
import com.smalaca.restapp.domain.product.ProductDto;
import com.smalaca.restapp.domain.product.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("{id}")
    public ProductDto findById(@PathVariable Long id) {
        return repository.findById(id).get().asDto();
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody ProductDto dto) {
        Product product = repository.findById(id).get();
        product.update(dto);
        repository.save(product);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping
    public Long create(@RequestBody ProductDto dto) {
        Product product = new Product(dto.getSerialNumber(), dto.getName(), dto.getPrice(), dto.getDescription(), dto.getShopId());

        return repository.save(product).getId();
    }
}
