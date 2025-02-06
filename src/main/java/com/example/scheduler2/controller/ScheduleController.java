package com.example.scheduler2.controller;

import com.example.scheduler2.dto.ScheduleRequestDto.CreateScheduleDto;
import com.example.scheduler2.dto.ScheduleResponseDto.CreateScheduleResultDto;
import com.example.scheduler2.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
