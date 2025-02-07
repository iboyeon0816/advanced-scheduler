package com.example.scheduler2.dto;

import com.example.scheduler2.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class CommentResponseDto {

    @Getter
    @AllArgsConstructor
    public static class CommentDetailDto {
        private final Long commentId;
        private final Long scheduleId;
        private final String authorName;
        private final String contents;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public CommentDetailDto(Comment comment) {
            this.commentId = comment.getId();
            this.scheduleId = comment.getSchedule().getId();
            this.authorName = comment.getUser().getName();
            this.contents = comment.getContents();
            this.createdAt = comment.getCreatedAt();
            this.updatedAt = comment.getUpdatedAt();
        }
    }
}
