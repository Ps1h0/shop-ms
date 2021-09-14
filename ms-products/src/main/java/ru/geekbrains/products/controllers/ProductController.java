package ru.geekbrains.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.corelib.exceptions.ResourceNotFoundException;
import ru.geekbrains.products.entities.Product;
import ru.geekbrains.products.entities.ProductDto;
import ru.geekbrains.products.repositories.specifications.ProductSpecifications;
import ru.geekbrains.products.services.ProductService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.findAll(ProductSpecifications.build(params), page, 4);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doens't exist"));
    }

    @GetMapping("/ids")
    public List<ProductDto> findProductById(@RequestParam List<Long> ids){
        return productService.findProductDtoByIds(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveNewProduct(@RequestBody ProductDto product) throws ParseException {
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto product) throws ParseException{
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
