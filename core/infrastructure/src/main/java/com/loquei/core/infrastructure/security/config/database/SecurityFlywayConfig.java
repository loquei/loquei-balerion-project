package com.loquei.core.infrastructure.security.config.database;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SecurityFlywayConfig {

    @Value("${security.datasource.driverClassName}")
    private String driverClassName;

    @Value("${security.datasource.url}")
    private String url;

    @Value("${security.datasource.username}")
    private String username;

    @Value("${security.datasource.password}")
    private String password;

    @Bean(name = "flywaySecurityDataSource")
    public DataSource flywaySecurityDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "securityFlyway")
    public Flyway securityFlyway(@Qualifier("flywaySecurityDataSource") DataSource flywaySecurityDataSource) {
        return Flyway.configure()
                .dataSource(flywaySecurityDataSource)
                .locations("classpath:db/migration/security")
                .load();
    }
}
