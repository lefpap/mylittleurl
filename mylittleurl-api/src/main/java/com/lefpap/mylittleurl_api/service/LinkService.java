package com.lefpap.mylittleurl_api.service;

import com.lefpap.mylittleurl_api.data.dto.CreateLinkRequest;
import com.lefpap.mylittleurl_api.data.dto.GetClickMetadataResponse;
import com.lefpap.mylittleurl_api.data.dto.GetLinkResponse;
import com.lefpap.mylittleurl_api.data.model.ClickMetadata;
import com.lefpap.mylittleurl_api.data.model.Link;
import com.lefpap.mylittleurl_api.data.model.LinkView;
import com.lefpap.mylittleurl_api.lib.UniqueCodeGenerator;
import com.lefpap.mylittleurl_api.mapper.ClickMetadataMapper;
import com.lefpap.mylittleurl_api.mapper.LinkMapper;
import com.lefpap.mylittleurl_api.repository.ClickMetadataRepository;
import com.lefpap.mylittleurl_api.repository.LinkRepository;
import com.lefpap.mylittleurl_api.repository.LinkViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkViewRepository linkViewRepository;
    private final ClickMetadataRepository metadataRepository;

    private final LinkMapper linkMapper;
    private final ClickMetadataMapper metadataMapper;

    private final UniqueCodeGenerator codeGenerator;

    public List<GetLinkResponse> findAll() {
        log.info("Retrieving all links");
        List<LinkView> links = linkViewRepository.findAll();
        return linkMapper.viewToGetLinkListResponse(links);
    }

    public GetLinkResponse findByCode(String code) {
        log.info("Finding link for code: {}", code);

        LinkView linkView = linkViewRepository.findByCode(code)
            .orElseThrow();

        return linkMapper.viewToGetLinkResponse(linkView);
    }

    @Transactional
    public GetLinkResponse create(CreateLinkRequest request) {

        log.info("Creating a new link for url: {}", request.url());
        String uniqueCode = codeGenerator.generateUniqueCode();

        Link link = linkMapper.fromCreateRequest(request).toBuilder()
            .code(uniqueCode)
            .build();

        Link createdLink = linkRepository.save(link);
        log.info("Successfully saved link with code: {}", createdLink.code());

        return linkMapper.toGetLinkResponse(createdLink);
    }

    public List<GetClickMetadataResponse> findLinkMetadataByCode(String code) {
        List<ClickMetadata> metadata = metadataRepository.findByLinkCode(code);
        return metadataMapper.toGetClickMetadataListResponse(metadata);
    }
}
