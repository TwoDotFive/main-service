package com.example.temp.baseball.service;

import com.example.temp.baseball.dto.TeamInformationView;

import java.util.List;

public interface FindAllTeamsInSeasonService {
    List<TeamInformationView> doService(int year);
}
