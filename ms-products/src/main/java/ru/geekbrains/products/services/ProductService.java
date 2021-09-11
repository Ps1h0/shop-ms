package ru.geekbrains.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.products.entities.Product;
import ru.geekbrains.products.entities.ProductDto;
import ru.geekbrains.products.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    //TODO допилить
//    public List<ProductDto> findProductDtoByIds(List<Long> ids){
//        return productRepository.findAll(ids);
//    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
