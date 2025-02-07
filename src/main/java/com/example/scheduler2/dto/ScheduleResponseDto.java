package com.example.scheduler2.dto;

import com.example.scheduler2.domain.Schedule;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponseDto {

    @Getter
    public static class ScheduleDetailDto {
        private final Long scheduleId;
        private final String authorName;
        private final String title;
        private final String contents;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final Long commentCount;

        public ScheduleDetailDto(Schedule schedule, Long commentCount) {
            this.scheduleId = schedule.getId();
            this.authorName = (schedule.getUser() != null) ? schedule.getUser().getName() : null;
            this.title = schedule.getTitle();
            this.contents = schedule.getContents();
            this.createdAt = schedule.getCreatedAt();
            this.updatedAt = schedule.getUpdatedAt();
            this.commentCount = commentCount;
        }
    }

    @Getter
    public static class SchedulePageDto {
        private final Integer totalPages;
        private final Long totalElements;
        private final Boolean isFirst;
        private final Boolean isLast;
        private final List<ScheduleDetailDto> data;

        public SchedulePageDto(Page<ScheduleDetailDto> schedulePage) {
            this.totalPages = schedulePage.getTotalPages();
            this.totalElements = schedulePage.getTotalElements();
            this.isFirst = schedulePage.isFirst();
            this.isLast = schedulePage.isLast();
            this.data = schedulePage.getContent();
        }
    }
}
