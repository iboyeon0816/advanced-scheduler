package com.example.scheduler2.dto;

import com.example.scheduler2.domain.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

public class ScheduleResponseDto {

    @Getter
    public static class CreateScheduleResultDto {
        private final Long scheduleId;
        private final String authorName;
        private final String title;
        private final String contents;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public CreateScheduleResultDto(Schedule schedule) {
            this.scheduleId = schedule.getId();
            this.authorName = schedule.getAuthorName();
            this.title = schedule.getTitle();
            this.contents = schedule.getContents();
            this.createdAt = schedule.getCreatedAt();
            this.updatedAt = schedule.getUpdatedAt();
        }
    }

    @Getter
    public static class FindScheduleResultDto {
        private final Long scheduleId;
        private final String authorName;
        private final String title;
        private final String contents;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public FindScheduleResultDto(Schedule schedule) {
            this.scheduleId = schedule.getId();
            this.authorName = schedule.getAuthorName();
            this.title = schedule.getTitle();
            this.contents = schedule.getContents();
            this.createdAt = schedule.getCreatedAt();
            this.updatedAt = schedule.getUpdatedAt();
        }
    }
}
