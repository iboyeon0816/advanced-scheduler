package com.example.scheduler2.controller;

import com.example.scheduler2.dto.UserRequestDto.SignUpDto;
import com.example.scheduler2.dto.UserResponseDto.UserDetailDto;
import com.example.scheduler2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDetailDto> signup(@Valid @RequestBody SignUpDto signUpDto) {
        UserDetailDto resultDto = userService.signup(signUpDto);
        return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
    }
}
