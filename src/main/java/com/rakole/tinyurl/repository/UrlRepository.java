package com.rakole.tinyurl.repository;

import com.rakole.tinyurl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Integer> {
}
