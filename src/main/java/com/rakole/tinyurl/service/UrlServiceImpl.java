package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.UrlService;
import com.rakole.tinyurl.exception.UrlNotFoundException;
import com.rakole.tinyurl.model.Url;
import com.rakole.tinyurl.repository.UrlRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url findById(int id) {
        Url url = urlRepository.findById(id).orElseThrow(UrlNotFoundException::new);
        return getUrl(url);
    }

    @Override
    public Url save(Url url) {
        return urlRepository.save(url);
    }

    @Override
    @Cacheable(cacheNames = "short_url_cache", key = "{#shortUrl}")
    public Url findByHash(String shortUrl) throws UrlNotFoundException {
        Url url = urlRepository.findByHash(shortUrl).orElseThrow(UrlNotFoundException::new);
        return getUrl(url);
    }

    protected Url getUrl(Url url) throws UrlNotFoundException {
        if (!url.isActive())

            throw new UrlNotFoundException();
        return url;
    }
}
