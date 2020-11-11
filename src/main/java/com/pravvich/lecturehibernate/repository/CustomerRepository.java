package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository {
    Customer create(final Customer customer);
    Optional<Customer> findById(final long customerId);
    List<Customer> findByFilter(final CustomerFilter filter);
    Optional<Customer> findByIdFetchProducts(final long customerId);
    List<Customer> findByFilterFetchProducts(final CustomerFilter filter);
}
