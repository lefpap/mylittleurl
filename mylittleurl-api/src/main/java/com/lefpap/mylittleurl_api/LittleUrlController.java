package com.lefpap.mylittleurl_api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class LittleUrlController {

    @Autowired
    private LittleUrlService urlService;

    @GetMapping("/{code}")
    public RedirectView resolve(@PathVariable String code) {
        return urlService.resolveUrlCode(code);
    }

    @GetMapping("/api/v1/urls/{code}")
    public LittleUrl getLittleUrl(@PathVariable String code) {
        return urlService.findByCode(code);
    }

    @PostMapping("/api/v1/urls")
    public LittleUrl createLittleUrl(@Valid @RequestBody LittleUrlRequestDTO request) {
        return urlService.create(request);
    }

}
