// package com.example.demo;

// import javax.sql.DataSource;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.orm.jpa.JpaVendorAdapter;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

// import jakarta.persistence.EntityManagerFactory;

// @Configuration
// public class JpaConfig {

//     @Bean
//     public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//         em.setDataSource(dataSource);
//         em.setPackagesToScan("com.example.demo");

//         JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//         em.setJpaVendorAdapter(vendorAdapter);

//         em.setEntityManagerFactoryInterface(EntityManagerFactory.class);
//         return em;
//     }
// }
