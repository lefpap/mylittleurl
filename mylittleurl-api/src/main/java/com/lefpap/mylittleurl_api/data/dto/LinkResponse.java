package com.lefpap.mylittleurl_api.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.Instant;

@Builder
public record LinkResponse(
    Long id,
    String code,
    String url,

    @JsonProperty("created_at")
    Instant createdAt,

    @JsonProperty("expires_at")
    Instant expiresAt,

    @JsonProperty("click_count")
    Integer clickCount
) {}
