package com.example.temp.baseball.service.impl;

import com.example.temp.baseball.domain.*;
import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.service.FindAllSchedulesDuringThisWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllSchedulesDuringThisWeekServiceImpl implements FindAllSchedulesDuringThisWeekService {
    private final GameRepository gameRepository;
    private final SeasonRepository seasonRepository;

    @Override
    public GameScheduleResponse doService(Team team, int year) {
        Season season = seasonRepository.findByYearOrElseThrow(year);

        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(7); // 다음 주 일요일 (exclusive)

        LocalDateTime startOfWeekDateTime = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekDateTime = endOfWeek.atStartOfDay();

        List<Game> found = gameRepository
                .findAllByTeamAndCurrentWeekInYear(season, team, startOfWeekDateTime, endOfWeekDateTime);
        return new GameScheduleResponse(found);
    }
}
