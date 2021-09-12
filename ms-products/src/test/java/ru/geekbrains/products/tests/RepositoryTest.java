package ru.geekbrains.products.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.geekbrains.products.entities.Product;
import ru.geekbrains.products.repositories.ProductRepository;

import java.util.List;


@DataJpaTest
public class RepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void initDbTest() {
        List<Product> genresList = productRepository.findAll();
        Assertions.assertEquals(3, genresList.size());
    }
}
