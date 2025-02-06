package com.example.scheduler2.service;

import com.example.scheduler2.domain.User;
import com.example.scheduler2.dto.UserRequestDto.SignUpDto;
import com.example.scheduler2.dto.UserResponseDto.UserDetailDto;
import com.example.scheduler2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDetailDto signup(SignUpDto signUpDto) {
        checkNotDuplicatedEmail(signUpDto.getName());

        User user = new User(signUpDto);
        userRepository.save(user);
        return new UserDetailDto(user);
    }

    private void checkNotDuplicatedEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
