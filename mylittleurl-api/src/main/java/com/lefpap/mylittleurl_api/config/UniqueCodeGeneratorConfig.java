package com.lefpap.mylittleurl_api.config;

import com.lefpap.mylittleurl_api.config.properties.UniqueCodeGeneratorConfigProperties;
import com.lefpap.mylittleurl_api.lib.RetryTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;

@Configuration
public class UniqueCodeGeneratorConfig {

    @Bean
    public RetryTemplate codeGeneratorTemplate(UniqueCodeGeneratorConfigProperties properties) {
        return RetryTemplate.builder()
            .maxAttempts(properties.maxAttempts())
            .retryableException(DbActionExecutionException.class)
            .build();
    }
}