package com.example.temp.baseball.service;

import com.example.temp.baseball.dto.TeamView;

import java.util.List;

public interface FindAllTeamsInSeasonService {
    List<TeamView> doService(int year);
}
