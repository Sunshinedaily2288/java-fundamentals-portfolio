package com.matharsa.repository;

import com.matharsa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // This allows us to look up an existing user by name before linking a ticket
    Optional<User> findByName(String name);
}
