package com.rakole.tinyurl.api;

import com.rakole.tinyurl.exception.UrlNotFoundException;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.model.dto.TinyUrlsResponseDto;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UrlService {
    Url findById(int id) throws UrlNotFoundException;

    Url save(Url url);

    Url findByHash(String shortUrl) throws UrlNotFoundException;

    String prepareTinyUrl(Url url);

    Url createStandAloneUrl(int userId, String url) throws NoSuchAlgorithmException;

    List<TinyUrlsResponseDto> getAllTinyUrlsForCurrentUser();
}
