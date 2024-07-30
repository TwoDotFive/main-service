package com.example.temp.baseball.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GameSchedulesRequest {
    private int season;
    private int teamId;
}
