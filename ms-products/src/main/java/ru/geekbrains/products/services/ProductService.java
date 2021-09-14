package ru.geekbrains.products.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.products.entities.Product;
import ru.geekbrains.products.entities.ProductDto;
import ru.geekbrains.products.repositories.ProductRepository;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ModelMapper modelMapper;

    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(this::toDto);
    }

    public List<ProductDto> findProductDtoByIds(List<Long> ids){
        return productRepository.findByIdIn(ids).stream().map(this::toDto).collect(Collectors.toList());
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(this::toDto);
    }

    public ProductDto saveOrUpdate(ProductDto product) throws ParseException {
        return toDto(productRepository.save(toEntity(product)));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDto toDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }

    private Product toEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
