package com.example.scheduler2.domain;

import com.example.scheduler2.domain.common.BaseEntity;
import com.example.scheduler2.dto.UserRequestDto.SignUpDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    public User(SignUpDto signUpDto) {
        this.name = signUpDto.getName();
        this.email = signUpDto.getEmail();
        this.password = signUpDto.getPassword();
    }
}
