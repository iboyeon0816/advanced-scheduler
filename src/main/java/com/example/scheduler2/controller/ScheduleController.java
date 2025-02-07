package com.example.scheduler2.controller;

import com.example.scheduler2.auth.SessionConst;
import com.example.scheduler2.dto.ScheduleRequestDto.CreateScheduleDto;
import com.example.scheduler2.dto.ScheduleRequestDto.UpdateScheduleDto;
import com.example.scheduler2.dto.ScheduleResponseDto.ScheduleDetailDto;
import com.example.scheduler2.dto.ScheduleResponseDto.SchedulePageDto;
import com.example.scheduler2.dto.UserSessionDto;
import com.example.scheduler2.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
    public ResponseEntity<ScheduleDetailDto> createSchedule(
            @SessionAttribute(name = SessionConst.LOGIN_USER) UserSessionDto sessionDto,
            @Valid @RequestBody CreateScheduleDto createDto
    ) {
        ScheduleDetailDto resultDto = scheduleService.createSchedule(sessionDto.getUserId(), createDto);
        return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDetailDto> findSchedule(@PathVariable Long scheduleId) {
        ScheduleDetailDto resultDto = scheduleService.findSchedule(scheduleId);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping
    public ResponseEntity<SchedulePageDto> findAllSchedules(@RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                            @RequestParam(defaultValue = "10") @Min(1) Integer size) {
        SchedulePageDto resultDto = scheduleService.findAllSchedules(page, size);
        return ResponseEntity.ok(resultDto);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDetailDto> updateSchedule(
            @SessionAttribute(name = SessionConst.LOGIN_USER) UserSessionDto sessionDto,
            @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleDto updateDto
    ) {
        scheduleService.updateSchedule(sessionDto.getUserId(), scheduleId, updateDto);
        ScheduleDetailDto resultDto = scheduleService.findSchedule(scheduleId);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name = SessionConst.LOGIN_USER) UserSessionDto sessionDto,
            @PathVariable Long scheduleId
    ) {
        scheduleService.deleteSchedule(sessionDto.getUserId(), scheduleId);
        return ResponseEntity.noContent().build();
    }
}
