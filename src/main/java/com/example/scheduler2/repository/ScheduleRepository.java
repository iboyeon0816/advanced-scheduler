package com.example.scheduler2.repository;

import com.example.scheduler2.domain.Schedule;
import com.example.scheduler2.exception.ex.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrThrowNotFound(Long scheduleId) {
        return findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Schedule", scheduleId));
    }
}
