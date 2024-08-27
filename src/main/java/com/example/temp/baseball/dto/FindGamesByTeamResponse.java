package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Game;

import java.util.List;

public record FindGamesByTeamResponse(
        List<GameView> games
) {
    public static FindGamesByTeamResponse build(List<Game> games) {
        List<GameView> result = games.stream().map(GameView::new).toList();
        return new FindGamesByTeamResponse(result);
    }
}
