package com.example.scheduler2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommentRequestDto {
    @Getter
    @AllArgsConstructor
    public static class CreateCommentDto {
        @Size(max = 100)
        @NotBlank
        private final String contents;
    }

    @Getter
    @AllArgsConstructor
    public static class UpdateCommentDto {
        @Size(max = 100)
        @NotBlank
        private final String contents;
    }
}
