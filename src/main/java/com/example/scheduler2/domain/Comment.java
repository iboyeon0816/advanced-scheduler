package com.example.scheduler2.domain;

import com.example.scheduler2.domain.common.BaseEntity;
import com.example.scheduler2.dto.CommentRequestDto.CreateCommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(CreateCommentDto createDto) {
        this.contents = createDto.getContents();
    }

    public void setUserAndSchedule(User user, Schedule schedule) {
        this.user = user;
        this.schedule = schedule;
    }
}
