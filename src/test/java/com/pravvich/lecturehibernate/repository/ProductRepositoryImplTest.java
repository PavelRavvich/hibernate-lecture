package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.config.H2JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = H2JpaConfig.class)
class ProductRepositoryImplTest {

    @Autowired
    ProductRepositoryImpl productRepositoryImpl;

    @Test
    public void test() {
    }

}