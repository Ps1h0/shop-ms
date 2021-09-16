package ru.geekbrains.routing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.routing.dtos.ProductDto;


import java.util.List;

@FeignClient("ms-products")
public interface ProductClient {

    @GetMapping("/api/v1/products/{id}")
    ProductDto findProductById(@PathVariable Long id);

    @GetMapping("/api/v1/products/ids")
    List<ProductDto> findProductByIds(@RequestParam List<Long> ids);
}
