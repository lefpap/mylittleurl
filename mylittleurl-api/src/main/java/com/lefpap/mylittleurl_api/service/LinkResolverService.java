package com.lefpap.mylittleurl_api.service;

import com.lefpap.mylittleurl_api.data.model.ClickMetadata;
import com.lefpap.mylittleurl_api.data.model.Link;
import com.lefpap.mylittleurl_api.mapper.ClickMetadataMapper;
import com.lefpap.mylittleurl_api.repository.ClickMetadataRepository;
import com.lefpap.mylittleurl_api.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LinkResolverService {

    private final LinkRepository linkRepository;
    private final ClickMetadataRepository metadataRepository;

    private final ClickMetadataMapper metadataMapper;

    @Transactional
    public String resolveLinkCode(String code, HttpHeaders headers) {
        log.info("Resolving link for code: {}", code);

        Link link = linkRepository.findByCode(code)
            .orElseThrow();

        ClickMetadata metadata = metadataMapper.fromHttpHeaders(headers).toBuilder()
            .linkRef(AggregateReference.to(link.id()))
            .build();

        metadataRepository.save(metadata);
        log.info("Saved new metadata for link: {}", link.id());

        log.info("Redirecting to URL: {} for code: {}", link.url(), code);
        return link.url();
    }
}
