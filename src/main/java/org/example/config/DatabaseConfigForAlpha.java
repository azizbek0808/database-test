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
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.example.repository.alpha",
        entityManagerFactoryRef = "alphaEntityManagerFactory",
        transactionManagerRef = "alphaTransactionManager")
public class DatabaseConfigForAlpha {

    @Bean
    @Primary
    @ConfigurationProperties("spring.alpha-datasource")
    public DataSourceProperties alphaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.alpha-datasource.configuration")
    public HikariDataSource alphaDataSource() {
        return alphaDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "alphaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("spring.jpa.show-sql", true);
        return builder
                .dataSource(alphaDataSource())
                .persistenceUnit("alpha_db")
                .properties(properties)
                .packages("org.example.domain.alpha")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager alphaTransactionManager(
            final @Qualifier("alphaEntityManagerFactory") LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory) {
        return new JpaTransactionManager(alphaEntityManagerFactory.getObject());
    }

    @Bean(name = "alphaEntityManager")
    public EntityManager alphaEntityManager(
            final @Qualifier("alphaEntityManagerFactory") LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory) {
        return alphaEntityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
    }


}
