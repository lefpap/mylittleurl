package com.lefpap.mylittleurl_api.controller;

import com.lefpap.mylittleurl_api.service.LinkResolverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class LinkResolverController {

    private final LinkResolverService linkResolverService;

    @GetMapping("/{code}")
    public RedirectView redirectToOriginalLink(@PathVariable String code, @RequestHeader HttpHeaders headers) {
        String url = linkResolverService.resolveLinkCode(code, headers);
        return new RedirectView(url, false, false);
    }
}
