package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Customer create(final Customer customer) {
        return null;
    }

    @Override
    public Customer findById(final long customerId) {
        return null;
    }

    @Override
    public List<Customer> findByFilter(final CustomerFilter filter) {
        return null;
    }
}
