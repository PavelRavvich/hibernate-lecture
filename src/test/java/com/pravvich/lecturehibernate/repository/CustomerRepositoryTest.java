package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.config.H2JpaConfig;
import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;
import com.pravvich.lecturehibernate.model.Product;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = H2JpaConfig.class)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void testFindByIdFetchProducts() {
        Customer customer = customerRepository.create(Customer.builder().age(20).name("Kiki").build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(300)).build());

        final Optional<Customer> result = customerRepository.findByIdFetchProducts(customer.getId());
        System.out.println(result.orElseThrow());

        assertEquals("Kiki", result.orElseThrow().getName());
    }


    @Test
    public void testFindByFilterFetchProducts() {
        Customer c1 = customerRepository.create(Customer.builder().age(20).name("John").build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(300)).build());
        Customer c2 = customerRepository.create(Customer.builder().age(30).name("Joscho").build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(400)).build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(500)).build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(600)).build());

        final CustomerFilter filter = new CustomerFilter("jo", 10, 35);
        final List<Customer> list = customerRepository.findByFilterFetchProducts(filter);
        System.out.println(list);

        assertEquals(2, list.size());
    }
}