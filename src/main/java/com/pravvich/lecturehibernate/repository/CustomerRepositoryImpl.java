package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
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
        final EntityManager entityManager = sessionFactory.createEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        final Root<Customer> customerMetamodel = criteriaQuery.from(Customer.class);
        criteriaQuery.where(criteriaBuilder.equal(customerMetamodel.get("id"), customerId));
        final TypedQuery<Customer> query = entityManager.createQuery(criteriaQuery);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Customer> findByIdEntityGraph(long customerId) {
        final EntityManager entityManager = sessionFactory.createEntityManager();
        final EntityGraph<?> entityGraph = entityManager.getEntityGraph("customer.products");
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", entityGraph);
        final Customer customer = entityManager.find(Customer.class, customerId, properties);
        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> findByFilter(final CustomerFilter filter) {
        // language = HQL
//        String query = "SELECT DISTINCT c FROM Customer c" +
//                " WHERE c.age BETWEEN :ageFrom AND :ageTo AND LOWER(c.name) LIKE :name";
//
//        try (final Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            @SuppressWarnings("unchecked")
//            final List<Customer> customers = session.createQuery(query)
//                    .setParameter("ageFrom", filter.getAgeFrom())
//                    .setParameter("ageTo", filter.getAgeTo())
//                    .setParameter("name", filter.getName())
//                    .list();
//            session.getTransaction().commit();
//            return customers;
//        }
        return null;
    }

    @Override
    public Optional<Customer> findByIdFetchJoin(final long customerId) {
        // language = HQL
//        final String HQL = "SELECT c FROM Customer c JOIN FETCH c.products p WHERE c.id = :id";
//
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            final Customer customer = session
//                    .createQuery(HQL, Customer.class)
//                    .setParameter("id", customerId)
//                    .getSingleResult();
//            session.getTransaction().commit();
//            return Optional.ofNullable(customer);
//        }
        return null;
    }

    @Override
    public List<Customer> findByFilterFetchJoin(final CustomerFilter filter) {
        // language = HQL
//        String query = "SELECT DISTINCT c FROM Customer c JOIN FETCH c.products p" +
//                        " WHERE c.age BETWEEN :ageFrom AND :ageTo AND lower(c.name) like :name";
//
//        try (final Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            @SuppressWarnings("unchecked")
//            final List<Customer> customers = session.createQuery(query)
//                    .setParameter("ageFrom", filter.getAgeFrom())
//                    .setParameter("ageTo", filter.getAgeTo())
//                    .setParameter("name", filter.getName())
//                    .list();
//            session.getTransaction().commit();
//            return customers;
//        }
        return null;
    }

    @Override
    public List<Customer> findByFilterEntityGraph(CustomerFilter filter) {
        // language = HQL
//        String query = "SELECT DISTINCT c FROM Customer c" +
//                " WHERE c.age BETWEEN :ageFrom AND :ageTo AND LOWER(c.name) LIKE :name";
//
//        try (final Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            final EntityGraph<?> entityGraph = session
//                    .getEntityManagerFactory()
//                    .createEntityManager()
//                    .getEntityGraph("customer.products");
//            @SuppressWarnings("unchecked")
//            final List<Customer> customers = session
//                    .createQuery(query)
//                    .setHint("javax.persistence.fetchgraph", entityGraph)
//                    .setParameter("ageFrom", filter.getAgeFrom())
//                    .setParameter("ageTo", filter.getAgeTo())
//                    .setParameter("name", filter.getName())
//                    .list();
//            session.getTransaction().commit();
//            return customers;
//        }
        return null;
    }

}
