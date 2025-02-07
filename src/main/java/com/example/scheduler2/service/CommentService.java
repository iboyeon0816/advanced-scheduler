package com.example.scheduler2.service;

import com.example.scheduler2.domain.Comment;
import com.example.scheduler2.domain.Schedule;
import com.example.scheduler2.domain.User;
import com.example.scheduler2.dto.CommentRequestDto.CreateCommentDto;
import com.example.scheduler2.dto.CommentResponseDto.CommentDetailDto;
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
}
