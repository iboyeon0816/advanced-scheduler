package com.example.scheduler2.dto;

import com.example.scheduler2.domain.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

public class ScheduleResponseDto {

    @Getter
    public static class ScheduleDetailDto {
        private final Long scheduleId;
        private final String authorName;
        private final String title;
        private final String contents;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public ScheduleDetailDto(Schedule schedule) {
            this.scheduleId = schedule.getId();
            this.authorName = (schedule.getUser() != null) ? schedule.getUser().getName() : null;
            this.title = schedule.getTitle();
            this.contents = schedule.getContents();
            this.createdAt = schedule.getCreatedAt();
            this.updatedAt = schedule.getUpdatedAt();
        }
    }
}
