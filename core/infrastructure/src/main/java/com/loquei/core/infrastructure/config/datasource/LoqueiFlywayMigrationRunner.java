package com.loquei.core.infrastructure.config.datasource;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LoqueiFlywayMigrationRunner {

    private final Flyway loqueiFlyway;

    public LoqueiFlywayMigrationRunner(@Qualifier("loqueiFlyway") final Flyway loqueiFlyway) {
        this.loqueiFlyway = loqueiFlyway;
    }

    @PostConstruct
    public void migrate() {
        loqueiFlyway.migrate();
    }
}
