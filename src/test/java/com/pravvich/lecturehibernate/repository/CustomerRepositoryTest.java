package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.config.H2JpaConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = H2JpaConfig.class)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void test() {
    }

}