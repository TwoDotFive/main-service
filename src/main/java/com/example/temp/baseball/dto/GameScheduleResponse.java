package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Game;
import lombok.Getter;

import java.util.List;

@Getter
public class GameScheduleResponse {
    private final List<GameView> games;
    private int numberOfGame;

    public GameScheduleResponse(List<Game> game) {
        this.games = game.stream().map(GameView::new).toList();
        this.numberOfGame = game.size();
    }
}
