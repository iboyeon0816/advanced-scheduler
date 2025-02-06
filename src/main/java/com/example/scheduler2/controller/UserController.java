package com.example.scheduler2.controller;

import com.example.scheduler2.auth.SessionConst;
import com.example.scheduler2.dto.UserRequestDto.LoginDto;
import com.example.scheduler2.dto.UserRequestDto.SignUpDto;
import com.example.scheduler2.dto.UserResponseDto.UserDetailDto;
import com.example.scheduler2.dto.UserSessionDto;
import com.example.scheduler2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto,
                                      HttpServletRequest request) {
        UserSessionDto sessionDto = userService.login(loginDto);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, sessionDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
