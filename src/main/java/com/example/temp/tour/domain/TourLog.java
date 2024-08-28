package com.example.temp.tour.domain;

import com.example.temp.baseball.domain.Stadium;
import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourLog extends BaseTimeEntity {

    @Id
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "stadium_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Stadium stadium;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "tourLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TourSchedule> schedules = new ArrayList<>();

    public TourLog(User user, Stadium stadium, String title) {
        this.id = IdUtil.create();
        this.user = user;
        this.stadium = stadium;
        this.title = title;
    }

    public void addTourSchedule(TourSchedule tourSchedule) {
        schedules.add(tourSchedule);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateTourSchedules(List<TourSchedule> schedules) {
        this.schedules.clear();
        this.schedules.addAll(schedules);
    }
}
