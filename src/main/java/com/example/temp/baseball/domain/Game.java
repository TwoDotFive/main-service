package com.example.temp.baseball.domain;

import com.example.temp.baseball.domain.value.GameState;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.*;

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
    private GameState state;

    @Builder
    public Game(Season season, Team homeTeam, Team awayTeam, LocalDateTime startDate) {
        this.id = IdUtil.create();
        this.season = season;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startDate = startDate;
        this.state = GameState.BEFORE_START;
    }
}
