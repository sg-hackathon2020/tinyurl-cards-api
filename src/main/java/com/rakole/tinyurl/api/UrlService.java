package com.rakole.tinyurl.api;

import com.rakole.tinyurl.exception.UrlNotFoundException;
import com.rakole.tinyurl.model.Url;

public interface UrlService {
    Url findById(int id) throws UrlNotFoundException;

    Url save(Url url);

    Url findByShortUrl(String shortUrl) throws UrlNotFoundException;
}
