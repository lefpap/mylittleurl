package com.lefpap.mylittleurl_api.mapper;

import com.lefpap.mylittleurl_api.data.dto.CreateLinkRequest;
import com.lefpap.mylittleurl_api.data.dto.LinkResponse;
import com.lefpap.mylittleurl_api.data.model.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class LinkMapper {

    public LinkResponse toResponse(Link link) {
        return LinkResponse.builder()
            .id(link.id())
            .code(link.code())
            .createdAt(link.createdAt())
            .expiresAt(link.expiresAt())
            .clickCount(link.metadata().size())
            .build();
    }

    public List<LinkResponse> toResponse(List<Link> links) {
        return links.stream()
            .map(this::toResponse)
            .toList();
    }

    public Link fromCreateRequest(CreateLinkRequest request) {
        return Link.builder()
            .url(request.url())
            .expiresAt(request.expiresAt())
            .build();
    }
}
