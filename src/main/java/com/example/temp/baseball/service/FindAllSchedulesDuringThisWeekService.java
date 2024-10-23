package com.example.temp.baseball.service;

import com.example.temp.baseball.dto.GameScheduleResponse;

public interface FindAllSchedulesDuringThisWeekService {
    GameScheduleResponse doService(long userId, int year);
}
