package com.example.scheduler2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
        @Size(max = 20)
        @Email
        private final String email;

        @NotBlank
        @Size(max = 20)
        private final String password;
    }
}
