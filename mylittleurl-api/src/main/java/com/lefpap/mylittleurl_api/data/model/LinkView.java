package com.lefpap.mylittleurl_api.data.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("link_view")
@Builder(toBuilder = true)
public record LinkView(
    @Id @ReadOnlyProperty Long id,
    @ReadOnlyProperty String code,
    @ReadOnlyProperty String url,
    @ReadOnlyProperty Instant createdAt,
    @ReadOnlyProperty Instant expiresAt,
    @ReadOnlyProperty Integer clickCount
) { }
