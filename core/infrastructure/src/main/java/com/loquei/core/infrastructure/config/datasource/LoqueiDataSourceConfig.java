package com.loquei.core.infrastructure.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.loquei.core.infrastructure.user",
        },
        entityManagerFactoryRef = "loqueiEntityManagerFactory",
        transactionManagerRef = "loqueiTransactionManager"
)
public class LoqueiDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "loquei.datasource")
    public LoqueiDataSourceProperties loqueiDataSourceProperties() {
        return new LoqueiDataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource loqueiDataSource(
            @Qualifier("loqueiDataSourceProperties") final LoqueiDataSourceProperties loqueiDataSourceProperties
    ) {
        return loqueiDataSourceProperties.initializeHikariDataSource();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean loqueiEntityManagerFactory(
            @Qualifier("loqueiDataSource") final DataSource loqueiDataSource
    ) {
        final var entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(loqueiDataSource);
        entityManagerFactory.setPackagesToScan("com.loquei.core.infrastructure");

        final var vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);

        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        return entityManagerFactory;
    }

    @Bean
    @Primary
    public PlatformTransactionManager loqueiTransactionManager(
            @Qualifier("loqueiDataSource") final DataSource loqueiDataSource
    ) {
        final var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                loqueiEntityManagerFactory(loqueiDataSource).getObject()
        );
        return transactionManager;
    }

}