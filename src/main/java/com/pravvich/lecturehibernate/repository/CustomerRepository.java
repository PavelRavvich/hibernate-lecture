package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository {
    Customer create(final Customer customer);

    // Without JOIN
    Optional<Customer> findById(final long customerId);
    List<Customer> findByFilter(final CustomerFilter filter);

    // Fetch join
    Optional<Customer> findByIdFetchJoin(final long customerId);

    // Entity Graph
    Optional<Customer> findByIdEntityGraph(final long customerId);
    List<Customer> findByFilterEntityGraph(final CustomerFilter filter);
}
