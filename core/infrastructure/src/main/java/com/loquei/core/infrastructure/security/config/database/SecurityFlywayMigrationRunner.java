package com.loquei.core.infrastructure.security.config.database;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SecurityFlywayMigrationRunner {

    private final Flyway securityFlyway;

    public SecurityFlywayMigrationRunner(@Qualifier("securityFlyway") final Flyway securityFlyway) {
        this.securityFlyway = securityFlyway;
    }

    @PostConstruct
    public void migrate() {
        securityFlyway.migrate();
    }
}
