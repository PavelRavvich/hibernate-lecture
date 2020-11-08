package com.pravvich.lecturehibernate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableJpaRepositories("com.pravvich")
@EnableTransactionManagement
public class JpaConfig {
}
