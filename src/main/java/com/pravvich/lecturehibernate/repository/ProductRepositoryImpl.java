package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.model.Product;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory;

    public Product save(final Product product) {
        return product;
    }
}
