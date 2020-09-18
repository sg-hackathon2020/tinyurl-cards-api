package com.rakole.tinyurl.repository;

import com.rakole.tinyurl.model.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TUserRepository extends JpaRepository<TUser, Integer> {
    boolean existsByEmail(String email);

    TUser findByEmail(String email);
}
