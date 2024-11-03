package com.lefpap.mylittleurl_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;

public record LittleUrlRequestDTO(
        @NotEmpty String url,

        @JsonProperty("expires_at")
        @FutureOrPresent
        Instant expiresAt
) {}
