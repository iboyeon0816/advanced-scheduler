package com.example.scheduler2.repository;

import com.example.scheduler2.domain.Comment;
import com.example.scheduler2.exception.ex.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrThrowNotFound(Long id) {
        return findById(id)
                .orElseThrow(() -> new NotFoundException("Comment", id));
    }
}
