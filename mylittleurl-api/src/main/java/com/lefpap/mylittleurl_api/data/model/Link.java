package com.lefpap.mylittleurl_api.data.model;

import lombok.Builder;
import lombok.Singular;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Set;

@Table("links")
@Builder(toBuilder = true)
public record Link(
    @Id Long id,
    String code,
    String url,
    @CreatedDate Instant createdAt,
    Instant expiresAt,

    @MappedCollection(idColumn = "link_id", keyColumn = "id")
    @Singular("metadata")
    Set<LinkClickMetadata> metadata
) { }
