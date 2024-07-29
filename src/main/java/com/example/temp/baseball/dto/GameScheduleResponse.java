package com.example.temp.baseball.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GameScheduleResponse {
    public LocalDateTime startDate;
    public LocalDateTime startTime;
    public TeamInformation home;
    public TeamInformation away;
    // 장소 full name, abbreviation name
}
