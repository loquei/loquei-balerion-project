package com.loquei.core.infrastructure.security.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.loquei.core.infrastructure.security",
        entityManagerFactoryRef = "securityEntityManagerFactory",
        transactionManagerRef = "securityTransactionManager")
@EnableTransactionManagement
public class SecurityDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "security.datasource")
    public SecurityDataSourceProperties securityDataSourceProperties() {
        return new SecurityDataSourceProperties();
    }

    @Bean
    public DataSource securityDataSource(
            @Qualifier("securityDataSourceProperties")
                    final SecurityDataSourceProperties securityDataSourceProperties) {
        return securityDataSourceProperties.initializeHikariDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(
            @Qualifier("securityDataSource") final DataSource securityDataSource) {
        final var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(securityDataSource);
        entityManagerFactoryBean.setPackagesToScan("com.loquei.core.infrastructure.security");

        final var vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);

        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager securityTransactionManager(
            @Qualifier("securityDataSource") final DataSource securityDataSource) {
        final var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                securityEntityManagerFactory(securityDataSource).getObject());
        return transactionManager;
    }
}
