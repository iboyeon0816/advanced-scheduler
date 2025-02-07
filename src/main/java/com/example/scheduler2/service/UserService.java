package com.example.scheduler2.service;

import com.example.scheduler2.config.PasswordEncoder;
import com.example.scheduler2.domain.User;
import com.example.scheduler2.dto.UserRequestDto.LoginDto;
import com.example.scheduler2.dto.UserRequestDto.SignUpDto;
import com.example.scheduler2.dto.UserRequestDto.UpdateUserDto;
import com.example.scheduler2.dto.UserResponseDto.UserDetailDto;
import com.example.scheduler2.dto.UserSessionDto;
import com.example.scheduler2.exception.ex.BadRequestException;
import com.example.scheduler2.exception.ex.UnauthorizedException;
import com.example.scheduler2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDetailDto signup(SignUpDto signUpDto) {
        checkNotDuplicatedEmail(signUpDto.getEmail());

        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        User user = new User(signUpDto.getName(), signUpDto.getEmail(), encodedPassword);
        userRepository.save(user);
        return new UserDetailDto(user);
    }

    public UserSessionDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                        .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));
        checkPassword(user.getPassword(), loginDto.getPassword());
        return new UserSessionDto(user);
    }

    public UserDetailDto findUser(Long userId) {
        User user = userRepository.findByIdOrThrowNotFound(userId);
        return new UserDetailDto(user);
    }

    @Transactional
    public void updateUser(Long userId, UpdateUserDto updateDto) {
        User user = userRepository.findByIdOrThrowNotFound(userId);
        user.setPassword(updateDto.getPassword());
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findByIdOrThrowNotFound(userId);
        userRepository.delete(user);
    }

    private void checkNotDuplicatedEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("This email is already registered");
        }
    }

    private void checkPassword(String password, String inputPassword) {
        if (!passwordEncoder.matches(inputPassword, password)) {
            throw new UnauthorizedException("Invalid email or password");
        }
    }
}
