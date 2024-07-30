package com.example.temp.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game {
    @Id
    private Long id;
    private Long season;
    private Long homeTeam;
    private Long awayTeam;
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
