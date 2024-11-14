package com.lefpap.mylittleurl_api.controller;

import com.lefpap.mylittleurl_api.data.dto.CreateLinkRequest;
import com.lefpap.mylittleurl_api.data.dto.GetClickMetadataResponse;
import com.lefpap.mylittleurl_api.data.dto.GetLinkResponse;
import com.lefpap.mylittleurl_api.service.LinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/links")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping
    public List<GetLinkResponse> listLinks() {
        return linkService.findAll();
    }

    @GetMapping("/{code}")
    public GetLinkResponse getLink(@PathVariable String code) {
        return linkService.findByCode(code);
    }

    @GetMapping("/{code}/meta")
    public List<GetClickMetadataResponse> getLinkMetadata(@PathVariable("code") String code) {
        return linkService.findLinkMetadataByCode(code);
    }

    @PostMapping
    public GetLinkResponse createLink(@Valid @RequestBody CreateLinkRequest request) {
        return linkService.create(request);
    }
}
