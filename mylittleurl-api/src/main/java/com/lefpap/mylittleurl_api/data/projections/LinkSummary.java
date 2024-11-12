package com.lefpap.mylittleurl_api.data.projections;

import java.time.Instant;

public record LinkSummary(
    Long id,
    String code,
    String url,
    Instant createdAt,
    Instant expiresAt,
    Integer clickCount
) {}
