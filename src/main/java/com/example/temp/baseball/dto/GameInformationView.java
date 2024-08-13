package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Game;

import java.time.LocalDateTime;

public record GameInformationView(
        long id,
        TeamInformationView awayTeam,
        TeamInformationView homeTeam,
        LocalDateTime startDate,
        String stadium
) {
    public GameInformationView(Game game) {
        this(
                game.getId(),
                new TeamInformationView(game.getAwayTeam()),
                new TeamInformationView(game.getHomeTeam()),
                game.getStartDate(),
                game.getHomeTeam().getName()
        );
    }
}

