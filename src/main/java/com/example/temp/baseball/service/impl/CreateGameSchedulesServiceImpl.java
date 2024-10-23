package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.Game;
import com.example.temp.baseball.domain.GameRepository;
import com.example.temp.baseball.domain.Season;
import com.example.temp.baseball.domain.SeasonRepository;
import com.example.temp.baseball.domain.Team;
import com.example.temp.baseball.domain.TeamRepository;
import com.example.temp.baseball.dto.GameSchedulesRequest;
import com.example.temp.baseball.service.CreateGameSchedulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateGameSchedulesServiceImpl implements CreateGameSchedulesService {

    private final GameRepository gameRepository;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public void doService(List<GameSchedulesRequest> gameSchedulesRequest) {
        for (GameSchedulesRequest req : gameSchedulesRequest) {

            Season season = seasonRepository.findByYearOrElseThrow(req.getSeason());
            Team homeTeam = teamRepository.findByIdOrElseThrow(req.getHomeTeam());
            Team awayTeam = teamRepository.findByIdOrElseThrow(req.getAwayTeam());

            Game saveGame = Game.builder()
                    .season(season)
                    .homeTeam(homeTeam)
                    .awayTeam(awayTeam)
                    .startDate(req.getStartDate())
                    .build();

            gameRepository.save(saveGame);
        }
    }
}
