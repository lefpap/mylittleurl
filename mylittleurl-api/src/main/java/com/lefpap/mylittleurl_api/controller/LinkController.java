package com.lefpap.mylittleurl_api.controller;

import com.lefpap.mylittleurl_api.data.dto.CreateLinkRequest;
import com.lefpap.mylittleurl_api.data.dto.LinkResponse;
import com.lefpap.mylittleurl_api.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/{code}")
    public RedirectView resolveLittleUrl(@PathVariable String code, @RequestHeader HttpHeaders headers) {
        return linkService.resolveUrlCode(code, headers);
    }

    @GetMapping("/api/v1/urls")
    public List<LinkResponse> listAllLittleUrls() {
        return linkService.findAll();
    }

    @GetMapping("/api/v1/urls/{code}")
    public LinkResponse getLittleUrl(@PathVariable String code) {
        return linkService.findByCode(code);
    }

    @PostMapping("/api/v1/urls")
    public LinkResponse createLittleUrl(@Valid @RequestBody CreateLinkRequest request) {
        return linkService.create(request);
    }

}
