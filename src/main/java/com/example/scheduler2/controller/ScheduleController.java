package com.example.scheduler2.controller;

import com.example.scheduler2.dto.ScheduleRequestDto.CreateScheduleDto;
import com.example.scheduler2.dto.ScheduleRequestDto.UpdateScheduleDto;
import com.example.scheduler2.dto.ScheduleResponseDto.ScheduleDetailDto;
import com.example.scheduler2.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDetailDto> createSchedule(@Valid @RequestBody CreateScheduleDto createDto) {
        ScheduleDetailDto resultDto = scheduleService.createSchedule(createDto);
        return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDetailDto> findSchedule(@PathVariable Long scheduleId) {
        ScheduleDetailDto resultDto = scheduleService.findSchedule(scheduleId);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDetailDto>> findAllSchedules() {
        List<ScheduleDetailDto> resultDtos = scheduleService.findAllSchedules();
        return ResponseEntity.ok(resultDtos);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDetailDto> updateSchedule(@PathVariable Long scheduleId,
                                                            @Valid @RequestBody UpdateScheduleDto updateDto) {
        scheduleService.updateSchedule(scheduleId, updateDto);
        ScheduleDetailDto resultDto = scheduleService.findSchedule(scheduleId);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
