package com.example.temp.baseball.service;

import com.example.temp.baseball.dto.GameScheduleResponse;
import com.example.temp.baseball.dto.GameSchedulesRequest;

import java.util.List;

public interface FindAllSchedulesService {
    public List<GameScheduleResponse> doService(GameSchedulesRequest request) ;
}
