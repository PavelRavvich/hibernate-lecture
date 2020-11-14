package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.config.H2JpaConfig;
import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;
import com.pravvich.lecturehibernate.model.Product;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = H2JpaConfig.class)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void prepareData() {
        // todo
    }

    @Test
    public void testFindById() {
        Customer customer = customerRepository.create(Customer.builder().age(20).name("Denis").build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(300)).build());


        final Customer result = customerRepository.findById(customer.getId()).orElseThrow();

        assertEquals("Denis", result.getName());
        assertThrows(LazyInitializationException.class, () -> System.out.println(result.getProducts()));
    }

    @Test
    public void testFindByIdFetchJoin() {
        Customer customer = customerRepository.create(Customer.builder().age(20).name("Kiki").build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(300)).build());

        final Customer result = customerRepository.findByIdFetchJoin(customer.getId()).orElseThrow();

        assertEquals("Kiki", result.getName());
        assertEquals(3, result.getProducts().size());
    }

    @Test
    public void testFindByIdEntityGraph() {
        Customer customer = customerRepository.create(Customer.builder().age(20).name("Vlad").build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(300)).build());

        final Customer result = customerRepository.findByIdEntityGraph(customer.getId()).orElseThrow();

        assertEquals("Vlad", result.getName());
        assertEquals(3, result.getProducts().size());
    }

    @Test
    public void testFindByFilter() {
        Customer c1 = customerRepository.create(Customer.builder().age(20).name("David").build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(300)).build());
        Customer c2 = customerRepository.create(Customer.builder().age(30).name("Dave").build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(400)).build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(500)).build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(600)).build());
        final CustomerFilter filter = new CustomerFilter("a", 10, 35);

        final List<Customer> result = customerRepository.findByFilter(filter);
        assertThrows(
                LazyInitializationException.class, () ->
                        result.forEach(customer ->
                                customer.getProducts().forEach(System.out::println)));

        assertEquals(2, result.size());
    }

    @Test
    public void testFindByFilterEntityGraph() {
        Customer c1 = customerRepository.create(Customer.builder().age(20).name("Max").build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(300)).build());
        Customer c2 = customerRepository.create(Customer.builder().age(30).name("Mari").build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(400)).build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(500)).build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(600)).build());

        final CustomerFilter filter = new CustomerFilter("ma", 10, 35);
        final List<Customer> list = customerRepository.findByFilterEntityGraph(filter);
        System.out.println(list);

        assertEquals(2, list.size());
    }

    @Test
    public void testFindByFilterFetchJoin() {
        Customer c1 = customerRepository.create(Customer.builder().age(20).name("Petr").build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(c1).price(new BigDecimal(300)).build());
        Customer c2 = customerRepository.create(Customer.builder().age(30).name("Per").build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(400)).build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(500)).build());
        productRepository.create(Product.builder().customer(c2).price(new BigDecimal(600)).build());

        final CustomerFilter filter = new CustomerFilter("pe", 10, 35);
        final List<Customer> list = customerRepository.findByFilterFetchJoin(filter);
        System.out.println(list);

        assertEquals(2, list.size());
    }


}