package com.lefpap.mylittleurl_api.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record GetLinkResponse(
    Long id,
    String code,
    String url,

    @JsonProperty("created_at")
    Instant createdAt,

    @JsonProperty("expires_at")
    Instant expiresAt,

    @JsonProperty("click_count")
    int clickCount
) { }
