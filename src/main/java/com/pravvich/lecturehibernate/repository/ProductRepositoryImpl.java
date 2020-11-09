package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.model.Product;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Product create(final Product product) {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final long id = (Long) session.save(product);
            session.getTransaction().commit();
            product.setId(id);
            return product;
        }
    }

    @Override
    public Optional<Product> findById(final long id) {
        // language=HQL
        final String HQL = "FROM Product p WHERE p.id = :id";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query<Product> query = session
                    .createQuery(HQL, Product.class)
                    .setParameter("id", id);
            final Product product = query.getSingleResult();
            return Optional.ofNullable(product);
        }
    }

}
