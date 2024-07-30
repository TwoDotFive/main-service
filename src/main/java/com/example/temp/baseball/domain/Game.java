package com.example.temp.baseball.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Game {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Season season;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team homeTeam;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team awayTeam;
    private LocalDateTime startDate;
    @Enumerated(EnumType.STRING)
    public State state;

    @RequiredArgsConstructor
    public enum State {
        BEFORE_START("경기 전"),
        IN_PROGRESS("경기중"),
        TERMINATED("경기종료");

        private final String kor;
    }
}
