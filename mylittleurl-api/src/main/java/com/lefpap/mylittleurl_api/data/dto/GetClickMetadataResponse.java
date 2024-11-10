package com.lefpap.mylittleurl_api.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.Instant;

@Builder
public record GetClickMetadataResponse(
    Long id,

    @JsonProperty("user_agent")
    String userAgent,

    @JsonProperty("referrer_url")
    String referrerUrl,

    @JsonProperty("clicked_at")
    Instant createdAt
) { }
