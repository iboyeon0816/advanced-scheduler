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

    @Column(nullable = false, length = 10)
    private String authorName;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(length = 100)
    private String contents;

    public Schedule(CreateScheduleDto createDto) {
        this.authorName = createDto.getAuthorName();
        this.title = createDto.getTitle();
        this.contents = createDto.getContents();
    }
}
