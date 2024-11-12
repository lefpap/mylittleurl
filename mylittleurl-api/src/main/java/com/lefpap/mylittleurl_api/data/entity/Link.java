package com.lefpap.mylittleurl_api.data.entity;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("links")
@Builder(toBuilder = true)
public record Link(
    @Id Long id,
    String code,
    String url,
    @CreatedDate Instant createdAt,
    Instant expiresAt
) { }
