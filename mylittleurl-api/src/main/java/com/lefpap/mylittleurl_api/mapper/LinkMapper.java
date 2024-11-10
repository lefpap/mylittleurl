package com.lefpap.mylittleurl_api.mapper;

import com.lefpap.mylittleurl_api.data.dto.CreateLinkRequest;
import com.lefpap.mylittleurl_api.data.dto.GetLinkResponse;
import com.lefpap.mylittleurl_api.data.model.Link;
import com.lefpap.mylittleurl_api.data.model.LinkView;
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


    public GetLinkResponse viewToGetLinkResponse(LinkView linkView) {
        if (linkView == null) {
            return null;
        }

        return GetLinkResponse.builder()
            .id(linkView.id())
            .code(linkView.code())
            .url(linkView.url())
            .clickCount(linkView.clickCount())
            .createdAt(linkView.createdAt())
            .expiresAt(linkView.expiresAt())
            .build();
    }

    public List<GetLinkResponse> viewToGetLinkListResponse(List<LinkView> linkViews) {
        if (linkViews == null || linkViews.isEmpty()) {
            return Collections.emptyList();
        }

        return linkViews.stream()
            .map(this::viewToGetLinkResponse)
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
