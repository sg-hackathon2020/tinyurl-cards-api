package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.api.UrlService;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.model.dto.TinyUrlsResponseDto;
import com.rakole.tinyurl.model.dto.UrlRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TinyUrlController {
    private final UrlService urlService;

    @Autowired
    public TinyUrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/tinyUrl", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UrlRequestDto> createStandAloneTinyUrl(@RequestBody UrlRequestDto urlRequestDto) throws NoSuchAlgorithmException {
        Url url = urlService.createStandAloneUrl(1, urlRequestDto.getUrl());
        return new ResponseEntity(UrlRequestDto.builder().url(urlService.prepareTinyUrl(url)).build()
                , HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "tinyUrls", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TinyUrlsResponseDto>> getAllUrlsForCurrentUser() {
        return new ResponseEntity(urlService.getAllTinyUrlsForCurrentUser(), HttpStatus.OK);
    }

}
