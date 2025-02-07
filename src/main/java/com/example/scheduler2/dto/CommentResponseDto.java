package com.example.scheduler2.dto;

import com.example.scheduler2.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseDto {

    @Getter
    @AllArgsConstructor
    public static class CommentDetailDto {
        private final Long commentId;
        private final String authorName;
        private final String contents;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public CommentDetailDto(Comment comment) {
            this.commentId = comment.getId();
            this.authorName = comment.getUser().getName();
            this.contents = comment.getContents();
            this.createdAt = comment.getCreatedAt();
            this.updatedAt = comment.getUpdatedAt();
        }
    }

    @Getter
    public static class CommentPageDto {
        private final Integer totalPages;
        private final Long totalElements;
        private final Boolean isFirst;
        private final Boolean isLast;
        private final List<CommentDetailDto> data;

        public CommentPageDto(Page<Comment> commentPage) {
            this.totalPages = commentPage.getTotalPages();
            this.totalElements = commentPage.getTotalElements();
            this.isFirst = commentPage.isFirst();
            this.isLast = commentPage.isLast();
            this.data = commentPage.getContent().stream()
                    .map(CommentDetailDto::new)
                    .toList();
        }
    }
}
