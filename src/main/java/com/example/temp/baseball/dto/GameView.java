package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Game;
import com.example.temp.baseball.domain.value.GameState;

import java.time.LocalDateTime;

public record GameView(
        String id,
        TeamView awayTeam,
        TeamView homeTeam,
        LocalDateTime startDate,
        String stadium,
        GameState state
) {
    public GameView(Game game) {
        this(
                Long.toString(game.getId()),
                new TeamView(game.getAwayTeam()),
                new TeamView(game.getHomeTeam()),
                game.getStartDate(),
                game.getHomeTeam().getName(),
                GameState.determineState(game.getStartDate())
        );
    }
}

