package com.example.scheduler2.controller;

import com.example.scheduler2.dto.ScheduleRequestDto.CreateScheduleDto;
import com.example.scheduler2.dto.ScheduleResponseDto.CreateScheduleResultDto;
import com.example.scheduler2.dto.ScheduleResponseDto.FindScheduleResultDto;
import com.example.scheduler2.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResultDto> createSchedule(@Valid @RequestBody CreateScheduleDto createDto) {
        CreateScheduleResultDto resultDto = scheduleService.createSchedule(createDto);
        return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<FindScheduleResultDto> findSchedule(@PathVariable Long scheduleId) {
        FindScheduleResultDto resultDto = scheduleService.findById(scheduleId);
        return ResponseEntity.ok(resultDto);
    }
}
