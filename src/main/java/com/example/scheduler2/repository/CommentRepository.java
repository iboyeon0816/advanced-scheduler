package com.example.scheduler2.repository;

import com.example.scheduler2.domain.Comment;
import com.example.scheduler2.exception.ex.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrThrowNotFound(Long commentId) {
        return findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment", commentId));
    }

    @Query("SELECT c FROM Comment c JOIN FETCH c.user u WHERE c.schedule.id = :scheduleId")
    Page<Comment> findAllByScheduleId(Long scheduleId, Pageable pageable);
}
