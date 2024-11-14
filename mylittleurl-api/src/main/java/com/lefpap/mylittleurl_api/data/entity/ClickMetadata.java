package com.lefpap.mylittleurl_api.data.entity;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("click_metadata")
@Builder(toBuilder = true)
public record ClickMetadata(
    @Id Long id,

    @Column("link_id")
    AggregateReference<Link, Long> linkRef,

    String userAgent,
    String referrerUrl,

    @CreatedDate
    Instant createdAt
) { }
