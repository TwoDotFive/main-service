package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.Game;
import com.example.temp.baseball.domain.GameRepository;
import com.example.temp.baseball.domain.Team;
import com.example.temp.baseball.domain.TeamRepository;
import com.example.temp.baseball.dto.FindGamesByTeamCommand;
import com.example.temp.baseball.dto.FindGamesByTeamResponse;
import com.example.temp.baseball.service.FindGamesByTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindGamesByTeamServiceImpl implements FindGamesByTeamService {
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;

    @Override
    public FindGamesByTeamResponse doService(FindGamesByTeamCommand command) {
        Team team = teamRepository.findByIdOrElseThrow(command.teamId());
        List<Game> games = gameRepository.findByTeamOrderByStartDate(team);
        return FindGamesByTeamResponse.build(games);
    }
}
