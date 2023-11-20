package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.example.repository.alfa",
        entityManagerFactoryRef = "alfaEntityManagerFactory",
        transactionManagerRef = "alfaTransactionManager")
public class DatabaseConfigForAlfa {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties analysisDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.configuration")
    public HikariDataSource analysisDataSource() {
        return analysisDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "alfaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean alfaEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(analysisDataSource())
                .persistenceUnit("alpha_db")
                .packages("org.example.domain.alfa")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager alfaTransactionManager(
            final @Qualifier("alfaEntityManagerFactory") LocalContainerEntityManagerFactoryBean alfaEntityManagerFactory) {
        return new JpaTransactionManager(alfaEntityManagerFactory.getObject());
    }

    @Bean(name = "alfaEntityManager")
    public EntityManager alfaEntityManager(
            final @Qualifier("alfaEntityManagerFactory") LocalContainerEntityManagerFactoryBean alfaEntityManagerFactory) {
        return alfaEntityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
    }


}
