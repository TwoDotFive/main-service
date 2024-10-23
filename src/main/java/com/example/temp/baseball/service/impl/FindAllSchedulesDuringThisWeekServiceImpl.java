package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.*;
import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.service.FindAllSchedulesDuringThisWeekService;
import com.example.temp.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllSchedulesDuringThisWeekServiceImpl implements FindAllSchedulesDuringThisWeekService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final SeasonRepository seasonRepository;

    @Override
    @Transactional(readOnly = true)
    public GameScheduleResponse doService(long userId, int year) {
        Team team = userRepository.findFavoriteTeam(userId);

        Season season = seasonRepository.findByYearOrElseThrow(year);

        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.minusDays(1);
        LocalDate endOfWeek = startOfWeek.plusDays(7);

        LocalDateTime startOfWeekDateTime = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekDateTime = endOfWeek.atStartOfDay();

        List<Game> found = gameRepository
                .findAllByTeamAndCurrentWeekInYear(season, team, startOfWeekDateTime, endOfWeekDateTime);
        return new GameScheduleResponse(found);
    }
}
