package com.example.temp.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
}
