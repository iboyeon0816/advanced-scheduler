package com.example.scheduler2.repository;

import com.example.scheduler2.domain.User;
import com.example.scheduler2.exception.ex.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    default User findByIdOrThrowNotFound(Long userId) {
        return findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
    }
}
