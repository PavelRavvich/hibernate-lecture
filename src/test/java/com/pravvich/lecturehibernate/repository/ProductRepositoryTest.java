package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.config.H2JpaConfig;
import com.pravvich.lecturehibernate.model.Product;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = H2JpaConfig.class)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void testCreate() {
        final Product product = new Product();
        product.setPrice(new BigDecimal("1"));
        final Product actual = productRepository.create(product);

        assertNotNull(actual.getId());
    }

    @Test
    public void testRead() {
        final Product product = new Product();
        product.setPrice(new BigDecimal("1.00"));
        final Product expected = productRepository.create(product);
        final Product actual = productRepository
                .findById(expected.getId())
                .orElseThrow();

        assertEquals(expected, actual);
    }
}