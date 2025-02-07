package com.example.scheduler2.service;

import com.example.scheduler2.domain.Comment;
import com.example.scheduler2.domain.Schedule;
import com.example.scheduler2.domain.User;
import com.example.scheduler2.dto.CommentRequestDto.CreateCommentDto;
import com.example.scheduler2.dto.CommentResponseDto.CommentDetailDto;
import com.example.scheduler2.exception.ex.BadRequestException;
import com.example.scheduler2.repository.CommentRepository;
import com.example.scheduler2.repository.ScheduleRepository;
import com.example.scheduler2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public CommentDetailDto createComment(Long userId, Long scheduleId, CreateCommentDto createDto) {
        User user = userRepository.findByIdOrThrowNotFound(userId);
        Schedule schedule = scheduleRepository.findByIdOrThrowNotFound(scheduleId);

        Comment comment = new Comment(createDto);
        comment.setUserAndSchedule(user, schedule);
        commentRepository.save(comment);

        return new CommentDetailDto(comment);
    }

    public CommentDetailDto findComment(Long scheduleId, Long commentId) {
        Comment comment = commentRepository.findByIdOrThrowNotFound(commentId);
        checkScheduleIdMatch(comment.getSchedule().getId(), scheduleId);
        return new CommentDetailDto(comment);
    }

    private void checkScheduleIdMatch(Long scheduleId, Long inputScheduleId) {
        if (!scheduleId.equals(inputScheduleId)) {
            String message = String.format("The comment does not belong to the schedule (ID: %s)", inputScheduleId);
            throw new BadRequestException(message);
        }
    }
}
