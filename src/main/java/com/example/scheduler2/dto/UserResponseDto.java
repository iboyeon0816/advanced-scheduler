package com.example.scheduler2.dto;

import com.example.scheduler2.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserResponseDto {

    @Getter
    public static class UserDetailDto {
        private final String name;
        private final String email;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public UserDetailDto(User user) {
            this.name = user.getName();
            this.email = user.getEmail();
            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
        }
    }
}
