package com.example.demo.database;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;

/**
 * <h1>DatabaseConfig</h1>
 * Database configuration.
 */
@Configuration
@EnableR2dbcAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Bean
    DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get("io.r2dbc:r2dbc-postgresql");
    }
}
