package com.loquei.core.infrastructure.security.config.database;

import lombok.Data;

@Data
public class SecurityHikariDataSourceProperties {
    private Boolean autoCommit;
    private Integer connectionTimeout;
    private Long maxLifetime;
    private Integer maximumPoolSize;
    private Integer minimumIdle;
    private String poolName;
}
