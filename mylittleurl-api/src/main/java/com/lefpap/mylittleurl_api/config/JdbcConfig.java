package com.lefpap.mylittleurl_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcAuditing
@EnableJdbcRepositories(basePackages = "com.lefpap.mylittleurl_api.repository", namedQueriesLocation = "classpath:queries.properties")
public class JdbcConfig extends AbstractJdbcConfiguration {
}
