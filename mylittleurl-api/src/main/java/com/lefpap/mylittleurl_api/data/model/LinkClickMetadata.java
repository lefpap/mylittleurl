package com.lefpap.mylittleurl_api.data.model;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("click_metadata")
@Builder(toBuilder = true)
public record LinkClickMetadata(
    @Id Long id,
    String userAgent,
    String referrerUrl,
    @CreatedDate Instant createdAt
) { }
