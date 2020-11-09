package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;

import java.util.List;


public interface CustomerRepository {
    Customer create(final Customer customer);
    Customer findById(final long customerId);
    List<Customer> findByFilter(final CustomerFilter filter);
}
