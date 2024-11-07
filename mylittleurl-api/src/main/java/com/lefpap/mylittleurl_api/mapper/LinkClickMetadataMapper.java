package com.lefpap.mylittleurl_api.mapper;

import com.lefpap.mylittleurl_api.data.model.LinkClickMetadata;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class LinkClickMetadataMapper {

    public LinkClickMetadata fromHttpHeaders(HttpHeaders headers) {
        return LinkClickMetadata.builder()
            .userAgent(headers.getFirst(HttpHeaders.USER_AGENT))
            .referrerUrl(headers.getFirst(HttpHeaders.REFERER))
            .createdAt(Instant.now())
            .build();
    }
}
