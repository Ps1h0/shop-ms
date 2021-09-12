package ru.geekbrains.products.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.products.entities.Product;
import ru.geekbrains.products.repositories.ProductRepository;
import ru.geekbrains.products.services.ProductService;

import java.util.Optional;

@SpringBootTest(classes = ProductService.class)
public class ServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetProduct(){
        Product testProduct = new Product();
        testProduct.setTitle("Test");
        testProduct.setPrice(100);
        testProduct.setId(50L);

        Mockito
                .doReturn(Optional.of(testProduct))
                .when(productRepository)
                .findById(50L);

        Product product = productService.findProductById(50L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(50L));
        Assertions.assertEquals("Test", product.getTitle());
    }
}
