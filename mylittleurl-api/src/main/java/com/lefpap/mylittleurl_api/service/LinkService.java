package com.lefpap.mylittleurl_api.service;

import com.lefpap.mylittleurl_api.data.dto.CreateLinkRequest;
import com.lefpap.mylittleurl_api.data.dto.LinkResponse;
import com.lefpap.mylittleurl_api.data.model.Link;
import com.lefpap.mylittleurl_api.data.model.LinkClickMetadata;
import com.lefpap.mylittleurl_api.lib.UniqueCodeGenerator;
import com.lefpap.mylittleurl_api.mapper.LinkClickMetadataMapper;
import com.lefpap.mylittleurl_api.mapper.LinkMapper;
import com.lefpap.mylittleurl_api.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;

    private final LinkMapper linkMapper;
    private final LinkClickMetadataMapper clickMetadataMapper;

    private final UniqueCodeGenerator codeGenerator;

    @Transactional
    public RedirectView resolveUrlCode(String code, HttpHeaders headers) {
        log.info("Resolving link for code: {}", code);

        Link.LinkBuilder linkBuilder = linkRepository.findByCode(code)
            .orElseThrow()
            .toBuilder();

        LinkClickMetadata metadata = clickMetadataMapper.fromHttpHeaders(headers);
        linkBuilder.metadata(metadata);

        Link updatedLink = linkRepository.save(linkBuilder.build());
        log.info("Saved new metadata for link: {}", updatedLink.id());

        log.info("Redirecting to URL: {} for code: {}", updatedLink.url(), code);
        return new RedirectView(updatedLink.url(), false, false);
    }

    public List<LinkResponse> findAll() {
        log.info("Retrieving all links");
        return linkMapper.toResponse(linkRepository.findAll());
    }

    public LinkResponse findByCode(String code) {
        log.info("Finding link for code: {}", code);
        Link link = linkRepository.findByCode(code)
            .orElseThrow();

        return linkMapper.toResponse(link);
    }

    @Transactional
    public LinkResponse create(CreateLinkRequest request) {

        log.info("Creating a new link for url: {}", request.url());
        String uniqueCode = codeGenerator.generateUniqueCode();

        Link link = linkMapper.fromCreateRequest(request).toBuilder()
            .code(uniqueCode)
            .url(request.url())
            .build();

        Link createdLink = linkRepository.save(link);
        log.info("Successfully saved link with code: {}", createdLink.code());

        return linkMapper.toResponse(createdLink);
    }

}
