package com.rakole.tinyurl.repository;

import com.rakole.tinyurl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Integer> {
    Optional<Url> findByHash(String shortUrl);

    boolean existsByHash(String shortUrl);
}
