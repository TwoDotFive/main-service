package com.example.temp.baseball.service;

import com.example.temp.baseball.dto.GameScheduleResponse;

import java.util.List;

public interface FindAllSchedulesService {
    public List<GameScheduleResponse> doService(long teamId) ;
}
