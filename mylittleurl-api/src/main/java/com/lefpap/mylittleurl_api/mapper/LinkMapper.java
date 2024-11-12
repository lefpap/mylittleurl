package com.lefpap.mylittleurl_api.mapper;

import com.lefpap.mylittleurl_api.data.dto.CreateLinkRequest;
import com.lefpap.mylittleurl_api.data.dto.GetLinkResponse;
import com.lefpap.mylittleurl_api.data.entity.Link;
import com.lefpap.mylittleurl_api.data.projections.LinkSummary;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class LinkMapper {

    public GetLinkResponse toGetLinkResponse(Link link) {
        if (link == null) {
            return null;
        }

        return GetLinkResponse.builder()
            .id(link.id())
            .code(link.code())
            .url(link.url())
            .createdAt(link.createdAt())
            .expiresAt(link.expiresAt())
            .build();
    }


    public GetLinkResponse summaryToGetLinkResponse(LinkSummary linkSummary) {
        if (linkSummary == null) {
            return null;
        }

        return GetLinkResponse.builder()
            .id(linkSummary.id())
            .code(linkSummary.code())
            .url(linkSummary.url())
            .clickCount(linkSummary.clickCount())
            .createdAt(linkSummary.createdAt())
            .expiresAt(linkSummary.expiresAt())
            .build();
    }

    public List<GetLinkResponse> summaryToGetLinkListResponse(List<LinkSummary> linkSummaries) {
        if (linkSummaries == null || linkSummaries.isEmpty()) {
            return Collections.emptyList();
        }

        return linkSummaries.stream()
            .map(this::summaryToGetLinkResponse)
            .filter(Objects::nonNull)
            .toList();
    }

    public Link fromCreateRequest(CreateLinkRequest request) {
        if (request == null) {
            return null;
        }

        return Link.builder()
            .url(request.url())
            .expiresAt(request.expiresAt())
            .build();
    }
}
