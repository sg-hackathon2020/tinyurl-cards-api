package com.rakole.tinyurl.repository;

import com.rakole.tinyurl.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
