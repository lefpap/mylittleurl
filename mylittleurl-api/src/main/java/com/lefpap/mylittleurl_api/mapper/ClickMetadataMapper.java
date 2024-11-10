package com.lefpap.mylittleurl_api.mapper;

import com.lefpap.mylittleurl_api.data.dto.GetClickMetadataResponse;
import com.lefpap.mylittleurl_api.data.model.ClickMetadata;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class ClickMetadataMapper {

    public ClickMetadata fromHttpHeaders(HttpHeaders headers) {
        if (headers == null) {
            return null;
        }

        return ClickMetadata.builder()
            .userAgent(headers.getFirst(HttpHeaders.USER_AGENT))
            .referrerUrl(headers.getFirst(HttpHeaders.REFERER))
            .createdAt(Instant.now())
            .build();
    }

    public GetClickMetadataResponse toGetClickMetadataResponse(ClickMetadata metadata) {
        if (metadata == null) {
            return null;
        }

        return GetClickMetadataResponse.builder()
            .id(metadata.id())
            .referrerUrl(metadata.referrerUrl())
            .userAgent(metadata.userAgent())
            .createdAt(metadata.createdAt())
            .build();
    }

    public List<GetClickMetadataResponse> toGetClickMetadataListResponse(List<ClickMetadata> metadata) {
        if (metadata == null || metadata.isEmpty()) {
            return Collections.emptyList();
        }

        return metadata.stream()
            .map(this::toGetClickMetadataResponse)
            .filter(Objects::nonNull)
            .toList();
    }
}
