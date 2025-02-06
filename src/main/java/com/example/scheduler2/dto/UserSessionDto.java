package com.example.scheduler2.dto;

import com.example.scheduler2.domain.User;
import lombok.Getter;

@Getter
public class UserSessionDto {
    private final Long userId;
    private final String name;
    private final String email;

    public UserSessionDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
