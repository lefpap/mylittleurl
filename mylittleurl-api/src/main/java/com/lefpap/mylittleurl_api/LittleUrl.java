package com.lefpap.mylittleurl_api;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.InsertOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Table("little_urls")
@Builder(toBuilder = true)
public record LittleUrl(
    @Id Long id,
    String code,
    String url,
    Long clickCount,
    Instant expiresAt,
    @InsertOnlyProperty Instant createdAt
) {

    public LittleUrl {
        if (clickCount == null) clickCount = 0L;
        if (createdAt == null) createdAt = Instant.now();
        if (expiresAt == null) expiresAt = Instant.now().plus(30, ChronoUnit.DAYS);
    }
}
