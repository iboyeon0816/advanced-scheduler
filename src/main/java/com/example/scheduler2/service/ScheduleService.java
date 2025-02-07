package com.example.scheduler2.service;

import com.example.scheduler2.domain.Schedule;
import com.example.scheduler2.domain.User;
import com.example.scheduler2.dto.ScheduleRequestDto.CreateScheduleDto;
import com.example.scheduler2.dto.ScheduleRequestDto.UpdateScheduleDto;
import com.example.scheduler2.dto.ScheduleResponseDto.ScheduleDetailDto;
import com.example.scheduler2.dto.ScheduleResponseDto.SchedulePageDto;
import com.example.scheduler2.exception.ex.ForbiddenException;
import com.example.scheduler2.repository.CommentRepository;
import com.example.scheduler2.repository.ScheduleRepository;
import com.example.scheduler2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public ScheduleDetailDto createSchedule(Long userId, CreateScheduleDto createDto) {
        User user = userRepository.findByIdOrThrowNotFound(userId);
        Schedule schedule = new Schedule(createDto);
        schedule.setUser(user);
        scheduleRepository.save(schedule);
        return new ScheduleDetailDto(schedule, 0L);
    }

    public ScheduleDetailDto findSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByIdOrThrowNotFound(scheduleId);
        Long commentCount = commentRepository.countByScheduleId(scheduleId);
        return new ScheduleDetailDto(schedule, commentCount);
    }

    public SchedulePageDto findAllSchedules(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(
                page - 1, size, Sort.by(Sort.Direction.DESC, "updatedAt")
        );
        Page<ScheduleDetailDto> schedulePage = scheduleRepository.findAllDtos(pageable);
        return new SchedulePageDto(schedulePage);
    }

    @Transactional
    public void updateSchedule(Long userId, Long scheduleId, UpdateScheduleDto updateDto) {
        Schedule schedule = scheduleRepository.findByIdOrThrowNotFound(scheduleId);
        checkScheduleAuthor(userId, schedule.getUser());
        schedule.update(updateDto.getTitle(), updateDto.getContents());
    }

    @Transactional
    public void deleteSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByIdOrThrowNotFound(scheduleId);
        checkScheduleAuthor(userId, schedule.getUser());
        scheduleRepository.delete(schedule);
    }

    private void checkScheduleAuthor(Long userId, User author) {
        if (author == null || !userId.equals(author.getId())) {
            throw new ForbiddenException("schedule");
        }
    }
}
