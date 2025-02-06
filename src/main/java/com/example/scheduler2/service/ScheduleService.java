package com.example.scheduler2.service;

import com.example.scheduler2.domain.Schedule;
import com.example.scheduler2.dto.ScheduleRequestDto.CreateScheduleDto;
import com.example.scheduler2.dto.ScheduleRequestDto.UpdateScheduleDto;
import com.example.scheduler2.dto.ScheduleResponseDto.ScheduleDetailDto;
import com.example.scheduler2.repository.ScheduleRepository;
import com.example.scheduler2.util.OptionalUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleDetailDto createSchedule(CreateScheduleDto createDto) {
        Schedule schedule = new Schedule(createDto);
        scheduleRepository.save(schedule);
        return new ScheduleDetailDto(schedule);
    }

    public ScheduleDetailDto findSchedule(Long scheduleId) {
        Schedule schedule = OptionalUtils.getOrThrowNotFound(scheduleRepository.findById(scheduleId));
        return new ScheduleDetailDto(schedule);
    }

    public List<ScheduleDetailDto> findAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleDetailDto::new)
                .toList();
    }

    @Transactional
    public void updateSchedule(Long scheduleId, UpdateScheduleDto updateDto) {
        Schedule schedule = OptionalUtils.getOrThrowNotFound(scheduleRepository.findById(scheduleId));
        schedule.update(updateDto.getTitle(), updateDto.getContents());
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = OptionalUtils.getOrThrowNotFound(scheduleRepository.findById(scheduleId));
        scheduleRepository.delete(schedule);
    }
}
