package com.lefpap.mylittleurl_api.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;

public record CreateLinkRequest(
        @NotEmpty String url,

        @JsonProperty("expires_at")
        @FutureOrPresent
        Instant expiresAt
) {}
