package com.pravvich.lecturehibernate.config;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories("com.pravvich")
@PropertySource("classpath:application.properties")
public class JpaConfig {

    private final DataSource dataSource;

    private final JpaVendorAdapter jpaVendorAdapter;

    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan("com.pravvich.lecturehibernate");
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPersistenceUnitName("default");
        emf.setDataSource(dataSource);
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public SessionFactory setSessionFactory(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

}
