package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final UrlRepository urlRepository;

    @Autowired
    public TestController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @GetMapping("getOne")
    public Url getUrl() {
        return urlRepository.findById(1).orElseGet(Url::new);
    }
}
