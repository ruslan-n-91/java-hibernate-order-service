package com.example.javahibernateorderservice.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class HibernateConfig {
    private final Environment env;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSource.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(30000);
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        return new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.connection.datasource", dataSource)
                        .applySetting("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"))
                        .applySetting("hibernate.show_sql", env.getRequiredProperty("spring.jpa.show-sql"))
                        .applySetting("hibernate.format_sql", env.getRequiredProperty("spring.jpa.properties.hibernate.format_sql"))
                        .applySetting("hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"))
                        .build()
        )
                .addAnnotatedClass(com.example.javahibernateorderservice.model.Order.class)
                .buildMetadata()
                .buildSessionFactory();
    }
}
