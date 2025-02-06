package com.example.scheduler2.service;

import com.example.scheduler2.domain.Schedule;
import com.example.scheduler2.dto.ScheduleRequestDto.CreateScheduleDto;
import com.example.scheduler2.dto.ScheduleResponseDto.CreateScheduleResultDto;
import com.example.scheduler2.dto.ScheduleResponseDto.FindScheduleResultDto;
import com.example.scheduler2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public CreateScheduleResultDto createSchedule(CreateScheduleDto createDto) {
        Schedule schedule = new Schedule(createDto);
        scheduleRepository.save(schedule);
        return new CreateScheduleResultDto(schedule);
    }

    public FindScheduleResultDto findSchedule(Long scheduleId) {
        Schedule schedule = getOrThrow(scheduleRepository.findById(scheduleId));
        return new FindScheduleResultDto(schedule);
    }

    public List<FindScheduleResultDto> findAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(FindScheduleResultDto::new)
                .toList();
    }

    public <T> T getOrThrow(Optional<T> entity) {
        return entity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
