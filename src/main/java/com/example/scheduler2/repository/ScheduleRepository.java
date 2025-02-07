package com.example.scheduler2.repository;

import com.example.scheduler2.domain.Schedule;
import com.example.scheduler2.dto.ScheduleResponseDto.ScheduleDetailDto;
import com.example.scheduler2.exception.ex.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrThrowNotFound(Long scheduleId) {
        return findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Schedule", scheduleId));
    }

    @Query("SELECT new com.example.scheduler2.dto.ScheduleResponseDto$ScheduleDetailDto(s, COUNT(*)) " +
            "FROM Schedule s " +
            "JOIN FETCH s.user u " +
            "LEFT JOIN Comment c ON s.id = c.schedule.id " +
            "GROUP BY s.id")
    List<ScheduleDetailDto> findAllDtos();
}
