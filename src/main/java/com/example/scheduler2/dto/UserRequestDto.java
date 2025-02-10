package com.example.scheduler2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserRequestDto {
    @Getter
    @AllArgsConstructor
    public static class SignUpDto {
        @NotBlank
        @Size(max = 10)
        private final String name;

        @NotBlank
        @Size(max = 30)
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "이메일 형식이어야 합니다"
        )
        private final String email;

        @NotBlank
        @Size(max = 20)
        private final String password;
    }

    @Getter
    @AllArgsConstructor
    public static class LoginDto {
        @NotBlank
        @Size(max = 30)
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "이메일 형식이어야 합니다"
        )
        private final String email;

        @NotBlank
        @Size(max = 20)
        private final String password;
    }

    @Getter
    @AllArgsConstructor
    public static class UpdateUserDto {
        @NotBlank
        @Size(max = 20)
        private final String currentPassword;

        @NotBlank
        @Size(max = 20)
        private final String newPassword;
    }
}
