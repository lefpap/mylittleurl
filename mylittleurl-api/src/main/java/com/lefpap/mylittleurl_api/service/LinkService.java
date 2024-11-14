package com.lefpap.mylittleurl_api.service;

import com.lefpap.mylittleurl_api.data.dto.CreateLinkRequest;
import com.lefpap.mylittleurl_api.data.dto.GetClickMetadataResponse;
import com.lefpap.mylittleurl_api.data.dto.GetLinkResponse;
import com.lefpap.mylittleurl_api.data.entity.ClickMetadata;
import com.lefpap.mylittleurl_api.data.entity.Link;
import com.lefpap.mylittleurl_api.data.projections.LinkSummary;
import com.lefpap.mylittleurl_api.lib.RetryTemplate;
import com.lefpap.mylittleurl_api.lib.UniqueCodeGenerator;
import com.lefpap.mylittleurl_api.mapper.ClickMetadataMapper;
import com.lefpap.mylittleurl_api.mapper.LinkMapper;
import com.lefpap.mylittleurl_api.repository.ClickMetadataRepository;
import com.lefpap.mylittleurl_api.repository.LinkRepository;
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
    private final ClickMetadataRepository metadataRepository;

    private final LinkMapper linkMapper;
    private final ClickMetadataMapper metadataMapper;

    private final UniqueCodeGenerator codeGenerator;
    private final RetryTemplate codeGeneratorTemplate;

    public List<GetLinkResponse> findAll() {
        log.info("Retrieving all links");
        List<LinkSummary> links = linkRepository.findSummaries();
        return linkMapper.summaryToGetLinkListResponse(links);
    }

    public GetLinkResponse findByCode(String code) {
        log.info("Finding link for code: {}", code);

        LinkSummary linkSummary = linkRepository.findSummaryByCode(code)
            .orElseThrow();

        return linkMapper.summaryToGetLinkResponse(linkSummary);
    }

    @Transactional
    public GetLinkResponse create(CreateLinkRequest request) {
        log.info("Creating a new link for url: {}", request.url());

        Link createdLink = codeGeneratorTemplate.execute(attempt -> {
            String uniqueCode = codeGenerator.generateUniqueCode();
            log.info("Generated code: {} (Attempt {}/{})", uniqueCode, attempt, codeGeneratorTemplate.getMaxAttempts());

            Link link = linkMapper.fromCreateRequest(request).toBuilder()
                .code(uniqueCode)
                .build();

            return linkRepository.save(link);
        });

        log.info("Successfully saved link with code: {}", createdLink.code());
        return linkMapper.toGetLinkResponse(createdLink);
    }

    public List<GetClickMetadataResponse> findLinkMetadataByCode(String code) {
        log.info("Retrieving click metadata for link code: {}", code);
        List<ClickMetadata> metadata = metadataRepository.findByLinkCode(code);
        return metadataMapper.toGetClickMetadataListResponse(metadata);
    }
}
