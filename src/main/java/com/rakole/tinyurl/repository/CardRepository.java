package com.rakole.tinyurl.repository;

import com.rakole.tinyurl.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
