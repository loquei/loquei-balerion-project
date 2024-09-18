package com.loquei.core.infrastructure.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;

@Data
public class LoqueiDataSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private LoqueiHikariDataSourceProperties hikari;

    public HikariDataSource initializeHikariDataSource() {
        final var hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(this.getUrl());
        hikariDataSource.setUsername(this.getUsername());
        hikariDataSource.setPassword(this.getPassword());
        hikariDataSource.setDriverClassName(this.getDriverClassName());
        hikariDataSource.setAutoCommit(this.getHikari().getAutoCommit());
        hikariDataSource.setConnectionTimeout(this.getHikari().getConnectionTimeout());
        hikariDataSource.setMaxLifetime(this.getHikari().getMaxLifetime());
        hikariDataSource.setMaximumPoolSize(this.getHikari().getMaximumPoolSize());
        hikariDataSource.setMinimumIdle(this.getHikari().getMinimumIdle());
        hikariDataSource.setPoolName(this.getHikari().getPoolName());
        return hikariDataSource;
    }
}
