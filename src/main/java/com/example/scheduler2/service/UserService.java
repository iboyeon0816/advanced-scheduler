package com.example.scheduler2.service;

import com.example.scheduler2.domain.User;
import com.example.scheduler2.dto.UserRequestDto.LoginDto;
import com.example.scheduler2.dto.UserRequestDto.SignUpDto;
import com.example.scheduler2.dto.UserResponseDto.UserDetailDto;
import com.example.scheduler2.dto.UserSessionDto;
import com.example.scheduler2.repository.UserRepository;
import com.example.scheduler2.util.OptionalUtils;
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
        checkNotDuplicatedEmail(signUpDto.getEmail());

        User user = new User(signUpDto);
        userRepository.save(user);
        return new UserDetailDto(user);
    }

    public UserSessionDto login(LoginDto loginDto) {
        User user = OptionalUtils.getOrThrowUnauthorized(
                userRepository.findByEmail(loginDto.getEmail())
        );
        checkPassword(user.getPassword(), loginDto.getPassword());
        return new UserSessionDto(user);
    }

    public UserDetailDto findUser(Long userId) {
        User user = OptionalUtils.getOrThrowNotFound(userRepository.findById(userId));
        return new UserDetailDto(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = OptionalUtils.getOrThrowNotFound(userRepository.findById(userId));
        userRepository.delete(user);
    }

    private void checkNotDuplicatedEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private static void checkPassword(String password, String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
