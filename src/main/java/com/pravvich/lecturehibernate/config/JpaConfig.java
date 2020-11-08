package com.pravvich.lecturehibernate.config;

import com.pravvich.lecturehibernate.model.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;

import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.pravvich")
@PropertySource("classpath:application.properties")
public class JpaConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.pravvich");

        Properties properties = new Properties();
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, 10);
        properties.put(AvailableSettings.HBM2DDL_AUTO, "validate");
        properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/hibernate_lecture");
        dataSource.setUsername("app");
        dataSource.setPassword("pas");
        return dataSource;
    }

}
