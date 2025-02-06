package com.example.scheduler2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ScheduleRequestDto {

    @Getter
    @AllArgsConstructor
    public static class CreateScheduleDto {
        @NotBlank
        @Size(max = 10)
        private final String authorName;

        @NotBlank
        @Size(max = 30)
        private final String title;

        @Size(max = 100)
        private final String contents;
    }
}
