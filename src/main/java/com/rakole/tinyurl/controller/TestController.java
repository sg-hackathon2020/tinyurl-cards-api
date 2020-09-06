package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.api.UrlService;
import com.rakole.tinyurl.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    UrlService urlService;

    @GetMapping("tiny")
    public Url getUrl() {
        return urlService.findByShortUrl("3255a");
    }
}
