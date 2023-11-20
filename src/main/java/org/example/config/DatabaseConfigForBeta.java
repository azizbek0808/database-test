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
@EnableJpaRepositories(basePackages = "org.example.repository.beta",
        entityManagerFactoryRef = "betaEntityManagerFactory",
        transactionManagerRef = "betaTransactionManager")
public class DatabaseConfigForBeta {

    @Bean
    @Primary
    @ConfigurationProperties("spring.beta-datasource")
    public DataSourceProperties betaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.beta-datasource.configuration")
    public HikariDataSource betaDataSource() {
        return betaDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "betaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean betaEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(betaDataSource())
                .persistenceUnit("beta_db")
                .packages("org.example.repository.beta")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager betaTransactionManager(
            final @Qualifier("betaEntityManagerFactory") LocalContainerEntityManagerFactoryBean betaEntityManagerFactory) {
        return new JpaTransactionManager(betaEntityManagerFactory.getObject());
    }

    @Bean(name = "betaEntityManager")
    public EntityManager betaEntityManager(
            final @Qualifier("betaEntityManagerFactory") LocalContainerEntityManagerFactoryBean betaEntityManagerFactory) {
        return betaEntityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
    }


}
