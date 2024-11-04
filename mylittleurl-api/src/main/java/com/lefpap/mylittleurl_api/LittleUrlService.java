package com.lefpap.mylittleurl_api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LittleUrlService {

    private final LittleUrlRepository urlRepository;
    private final LittleUrlCodeGen urlCodeGen;

    public RedirectView resolveUrlCode(String code) {
        log.info("Resolving URL for code: {}", code);
        String fullUrl = urlRepository.findByCode(code)
            .map(this::updateUrlClickCount)
            .map(LittleUrl::url)
            .orElseThrow();

        log.info("Redirecting to URL: {} for code: {}", fullUrl, code);
        return new RedirectView(fullUrl, false, false);
    }

    private LittleUrl updateUrlClickCount(LittleUrl littleUrl) {
        long updatedClickCount = littleUrl.clickCount() + 1;

        log.debug("Updating click count for code: {} to {}", littleUrl.code(), updatedClickCount);
        return urlRepository.save(littleUrl.toBuilder()
            .clickCount(updatedClickCount)
            .build()
        );
    }

    public LittleUrl findByCode(String code) {
        log.info("Finding URL for code: {}", code);
        return urlRepository.findByCode(code)
                .orElseThrow();
    }

    @Transactional
    public LittleUrl create(LittleUrlRequestDTO request) {

        log.info("Creating a new shortened URL for: {}", request.url());
        String code = urlCodeGen.generateUniqueCode();

        // Build the LittleUrl entity
        LittleUrl.LittleUrlBuilder builder = LittleUrl.builder()
                .code(code)
                .url(request.url());

        Optional.ofNullable(request.expiresAt()).ifPresent(builder::expiresAt);

        LittleUrl littleUrl = urlRepository.save(builder.build());
        log.info("Successfully saved shortened URL with code: {}", littleUrl.code());

        return littleUrl;
    }

}
