package com.example.temp.baseball.service;

import com.example.temp.baseball.domain.Team;
import com.example.temp.baseball.dto.GameScheduleResponse;

import java.util.List;

public interface FindAllSchedulesDuringThisWeekService {
    List<GameScheduleResponse> doService(Team team, int year);
}
