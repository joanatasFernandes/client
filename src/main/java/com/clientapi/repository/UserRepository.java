package com.clientapi.repository;

import com.clientapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findByUsername(String username);
}
