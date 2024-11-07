package com.lefpap.mylittleurl_api.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "code-gen")
public record CodeGeneratorConfig(

    @NotBlank
    String allowedCharacters,

    @Length(min = 3, max = 10)
    int codeLength,

    @Positive
    int maxAttempts
) {}
