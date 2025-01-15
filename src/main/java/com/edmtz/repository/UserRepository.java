package com.edmtz.repository;

import com.edmtz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository {
    Optional<User> findByEmail(String email);
}
