package com.lefpap.mylittleurl_api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LittleUrlService {

    private final LittleUrlRepository urlRepository;
    private final LittleUrlCodeGen urlCodeGen;

    public RedirectView resolveUrlCode(String code) {
        String fullUrl = urlRepository.findByCode(code)
            .map(this::updateUrlClickCount)
            .map(LittleUrl::url)
            .orElseThrow();

        return new RedirectView(fullUrl);
    }

    private LittleUrl updateUrlClickCount(LittleUrl littleUrl) {
        return urlRepository.save(littleUrl.toBuilder()
            .clickCount(littleUrl.clickCount() + 1)
            .build()
        );
    }

    public LittleUrl findByCode(String code) {
        return urlRepository.findByCode(code)
                .orElseThrow();
    }

    public LittleUrl create(LittleUrlRequestDTO request) {
        return urlRepository.findByUrl(request.url())
                .orElse(buildNewLittleUrl(request));
    }

    private LittleUrl buildNewLittleUrl(LittleUrlRequestDTO request) {
        LittleUrl.LittleUrlBuilder builder = LittleUrl.builder();
        builder.code(urlCodeGen.generateUniqueCode());
        builder.url(request.url());
        Optional.ofNullable(request.expiresAt()).ifPresent(builder::expiresAt);

        return urlRepository.save(builder.build());
    }

}
