package com.example.scheduler2.controller;

import com.example.scheduler2.auth.SessionConst;
import com.example.scheduler2.dto.CommentRequestDto.CreateCommentDto;
import com.example.scheduler2.dto.CommentRequestDto.UpdateCommentDto;
import com.example.scheduler2.dto.CommentResponseDto.CommentDetailDto;
import com.example.scheduler2.dto.CommentResponseDto.CommentPageDto;
import com.example.scheduler2.dto.UserSessionDto;
import com.example.scheduler2.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDetailDto> findComment(@PathVariable Long scheduleId,
                                                        @PathVariable Long commentId) {
        CommentDetailDto resultDto = commentService.findComment(scheduleId, commentId);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping
    public ResponseEntity<CommentPageDto> findAllComments(
            @PathVariable Long scheduleId,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size
    ) {
        CommentPageDto resultDto = commentService.findAllComments(scheduleId, page, size);
        return ResponseEntity.ok(resultDto);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDetailDto> updateComment(
            @SessionAttribute(name = SessionConst.LOGIN_USER) UserSessionDto sessionDto,
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentDto updateDto
    ) {
        commentService.updateComment(sessionDto.getUserId(), scheduleId, commentId, updateDto);
        CommentDetailDto resultDto = commentService.findComment(commentId);
        return ResponseEntity.ok(resultDto);
    }
}
