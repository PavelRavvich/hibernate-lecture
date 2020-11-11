package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Customer create(final Customer customer) {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final long id = (Long) session.save(customer);
            session.getTransaction().commit();
            customer.setId(id);
            return customer;
        }
    }

    @Override
    public Optional<Customer> findById(final long customerId) {
        // language=HQL
        final String HQL = "SELECT c FROM Customer c  WHERE c.id = :id";

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Customer customer = session
                    .createQuery(HQL, Customer.class)
                    .setParameter("id", customerId)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.ofNullable(customer);
        }
    }

    @Override
    public List<Customer> findByFilter(final CustomerFilter filter) {
        // language = HQL
        String query = "SELECT DISTINCT c FROM Customer c" +
                " WHERE c.age BETWEEN :ageFrom AND :ageTo AND LOWER(c.name) LIKE :name";

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            final List<Customer> list = session.createQuery(query)
                    .setParameter("ageFrom", filter.getAgeFrom())
                    .setParameter("ageTo", filter.getAgeTo())
                    .setParameter("name", filter.getName())
                    .list();
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<Customer> findByIdFetchProducts(final long customerId) {
        // language = HQL
        final String HQL = "SELECT c FROM Customer c JOIN FETCH c.products p WHERE c.id = :id";

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Customer customer = session
                    .createQuery(HQL, Customer.class)
                    .setParameter("id", customerId)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.ofNullable(customer);
        }
    }

    @Override
    public List<Customer> findByFilterFetchProducts(final CustomerFilter filter) {
        // language = HQL
        String query = "SELECT DISTINCT c FROM Customer c JOIN FETCH c.products p" +
                        " WHERE c.age BETWEEN :ageFrom AND :ageTo AND lower(c.name) like :name";

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            final List<Customer> list = session.createQuery(query)
                    .setParameter("ageFrom", filter.getAgeFrom())
                    .setParameter("ageTo", filter.getAgeTo())
                    .setParameter("name", filter.getName())
                    .list();
            session.getTransaction().commit();
            return list;
        }
    }

}
