package com.loquei.core.infrastructure.config.datasource;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class LoqueiFlywayConfig {

    @Value("${loquei.datasource.driverClassName}")
    private String driverClassName;

    @Value("${loquei.datasource.url}")
    private String url;

    @Value("${loquei.datasource.username}")
    private String username;

    @Value("${loquei.datasource.password}")
    private String password;

    @Bean(name = "flywayLoqueiDataSource")
    public DataSource flywayLoqueiDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "loqueiFlyway")
    public Flyway loqueiFlyway(@Qualifier("flywayLoqueiDataSource") DataSource flywayLoqueiDataSource) {
        return Flyway.configure()
                .dataSource(flywayLoqueiDataSource)
                .locations("classpath:db/migration/loquei")
                .load();
    }
}
