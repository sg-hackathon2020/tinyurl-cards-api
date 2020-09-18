package com.rakole.tinyurl.repository;

import com.rakole.tinyurl.model.Card;
import com.rakole.tinyurl.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findAllByGroup(Group group);
}
