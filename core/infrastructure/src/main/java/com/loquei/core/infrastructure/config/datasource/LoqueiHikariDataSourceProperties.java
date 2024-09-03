package com.loquei.core.infrastructure.config.datasource;

import lombok.Data;

@Data
public class LoqueiHikariDataSourceProperties {
    private Boolean autoCommit;
    private Integer connectionTimeout;
    private Long maxLifetime;
    private Integer maximumPoolSize;
    private Integer minimumIdle;
    private String poolName;
}
