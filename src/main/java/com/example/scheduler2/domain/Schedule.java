package com.example.scheduler2.domain;

import com.example.scheduler2.domain.common.BaseEntity;
import com.example.scheduler2.dto.ScheduleRequestDto.CreateScheduleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(length = 100)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(CreateScheduleDto createDto) {
        this.title = createDto.getTitle();
        this.contents = createDto.getContents();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
