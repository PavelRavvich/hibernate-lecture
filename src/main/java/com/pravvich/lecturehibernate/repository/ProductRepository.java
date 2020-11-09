package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.model.Product;

import java.util.Optional;


public interface ProductRepository {
    Product create(final Product product);
    Optional<Product> findById(final long id);
}
