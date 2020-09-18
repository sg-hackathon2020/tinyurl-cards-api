package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.api.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectUrlController {

    private final UrlService urlService;

    @Autowired
    public RedirectUrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(value = "{shortUrl}")
    public RedirectView testCardDto(@PathVariable String shortUrl) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urlService.findByHash(shortUrl).getUrl());
        return redirectView;
    }
}
