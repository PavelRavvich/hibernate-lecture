package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.config.H2JpaConfig;
import com.pravvich.lecturehibernate.filter.CustomerFilter;
import com.pravvich.lecturehibernate.model.Customer;
import com.pravvich.lecturehibernate.model.Product;
import org.hibernate.LazyInitializationException;
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
    public void testFindByIdFetchProducts() {
        Customer customer = customerRepository.create(Customer.builder().age(20).name("Kiki").build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(100)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(200)).build());
        productRepository.create(Product.builder().customer(customer).price(new BigDecimal(300)).build());

        final Customer result = customerRepository.findByIdFetchProducts(customer.getId()).orElseThrow();

        assertEquals("Kiki", result.getName());
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
        final CustomerFilter filter = new CustomerFilter("da", 10, 35);

        final List<Customer> result = customerRepository.findByFilter(filter);
        assertThrows(
                LazyInitializationException.class, () ->
                        result.forEach(customer ->
                                customer.getProducts().forEach(System.out::println)));

        assertEquals(2, result.size());
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