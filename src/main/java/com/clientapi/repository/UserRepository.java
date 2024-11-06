package com.clientapi.repository;

import com.clientapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    UserEntity findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE token = ?1", nativeQuery = true)
    UserEntity findByToken(String token);
}
