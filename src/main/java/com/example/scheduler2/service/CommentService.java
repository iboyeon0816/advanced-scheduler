package com.example.scheduler2.service;

import com.example.scheduler2.domain.Comment;
import com.example.scheduler2.domain.Schedule;
import com.example.scheduler2.domain.User;
import com.example.scheduler2.dto.CommentRequestDto.CreateCommentDto;
import com.example.scheduler2.dto.CommentRequestDto.UpdateCommentDto;
import com.example.scheduler2.dto.CommentResponseDto.CommentDetailDto;
import com.example.scheduler2.dto.CommentResponseDto.CommentPageDto;
import com.example.scheduler2.exception.ex.BadRequestException;
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
public class CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentDetailDto createComment(Long userId, Long scheduleId, CreateCommentDto createDto) {
        User user = userRepository.findByIdOrThrowNotFound(userId);
        Schedule schedule = scheduleRepository.findByIdOrThrowNotFound(scheduleId);

        Comment comment = new Comment(createDto);
        comment.setUserAndSchedule(user, schedule);
        commentRepository.save(comment);

        return new CommentDetailDto(comment);
    }

    public CommentDetailDto findComment(Long commentId) {
        Comment comment = commentRepository.findByIdOrThrowNotFound(commentId);
        return new CommentDetailDto(comment);
    }

    public CommentDetailDto findComment(Long scheduleId, Long commentId) {
        Comment comment = commentRepository.findByIdOrThrowNotFound(commentId);
        checkScheduleIdMatch(comment.getSchedule().getId(), scheduleId);
        return new CommentDetailDto(comment);
    }

    public CommentPageDto findAllComments(Long scheduleId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(
                page - 1, size, Sort.by(Sort.Direction.DESC, "updatedAt")
        );
        Page<Comment> commentPage = commentRepository.findAllByScheduleId(scheduleId, pageable);
        return new CommentPageDto(commentPage);
    }

    @Transactional
    public void updateComment(Long userId, Long scheduleId, Long commentId, UpdateCommentDto updateDto) {
        Comment comment = commentRepository.findByIdOrThrowNotFound(commentId);

        checkScheduleIdMatch(comment.getSchedule().getId(), scheduleId);
        checkCommentAuthor(userId, comment.getUser());

        comment.setContents(updateDto.getContents());
    }

    @Transactional
    public void deleteComment(Long userId, Long scheduleId, Long commentId) {
        Comment comment = commentRepository.findByIdOrThrowNotFound(commentId);

        checkScheduleIdMatch(comment.getSchedule().getId(), scheduleId);
        checkCommentAuthor(userId, comment.getUser());

        commentRepository.delete(comment);
    }

    private void checkScheduleIdMatch(Long scheduleId, Long inputScheduleId) {
        if (!scheduleId.equals(inputScheduleId)) {
            String message = String.format("The comment does not belong to the schedule (ID: %s)", inputScheduleId);
            throw new BadRequestException(message);
        }
    }

    private void checkCommentAuthor(Long userId, User author) {
        if (author == null || !userId.equals(author.getId())) {
            throw new ForbiddenException("Comment");
        }
    }
}
