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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindGamesByTeamServiceImpl implements FindGamesByTeamService {
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;

    @Override
    @Transactional(readOnly = true)
    public FindGamesByTeamResponse doService(FindGamesByTeamCommand command) {
        Team team = teamRepository.findByIdOrElseThrow(command.teamId());
        List<Game> games = gameRepository.findByTeamOrDateOrderByStartDate(team, command.startDate(), command.endDate());

        return FindGamesByTeamResponse.build(games);
    }
}
