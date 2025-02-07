package com.example.scheduler2.controller;

import com.example.scheduler2.auth.SessionConst;
import com.example.scheduler2.dto.CommentRequestDto.CreateCommentDto;
import com.example.scheduler2.dto.CommentResponseDto.CommentDetailDto;
import com.example.scheduler2.dto.UserSessionDto;
import com.example.scheduler2.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDetailDto> createComment(
            @SessionAttribute(name = SessionConst.LOGIN_USER) UserSessionDto sessionDto,
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentDto createDto
    ) {
        CommentDetailDto resultDto = commentService.createComment(sessionDto.getUserId(), scheduleId, createDto);
        return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
    }
}
