package ru.geekbrains.routing.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.products.entities.ProductDto;

import java.util.List;

@FeignClient("ms-products")
public interface ProductClient {

    @GetMapping("/api/v1/{id}")
    ProductDto findProductById(@PathVariable Long id);

    @GetMapping("/api/v1/ids")
    List<ProductDto> findProductByIds(@RequestParam List<Long> ids);

    @PostMapping("/api/v1")
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto saveNewProduct(@RequestBody ProductDto product);

    @PutMapping("/api/v1")
    ProductDto updateProduct(@RequestBody ProductDto product);

    @DeleteMapping("/api/v1/{id}")
    ProductDto updateProduct(@PathVariable Long id);
}
