package com.smalaca.restapp.api.rest.product;

import com.smalaca.restapp.domain.product.Product;
import com.smalaca.restapp.domain.product.ProductDto;
import com.smalaca.restapp.domain.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
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
    public List<ProductDto> findAll(@RequestParam Map<String, String> params, @RequestParam(required = false) List<Long> shopIds) {
        Iterable<Product> found;

        if (shopIds != null) {
            found = repository.findAllByShopIdIn(shopIds);
        } else if (params.containsKey("serialNumber") && params.containsKey("name")) {
            found = repository.findAllBySerialNumberOrName(params.get("serialNumber"), params.get("name"));
        } else {
            found = repository.findAll();
        }

        return StreamSupport.stream(found.spliterator(), false)
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
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ProductDto dto) {
        if (repository.existsBySerialNumber(dto.getSerialNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            Product product = new Product(dto.getSerialNumber(), dto.getName(), dto.getPrice(), dto.getDescription(), dto.getShopId());
            Long id = repository.save(product).getId();

            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        }
    }
}
