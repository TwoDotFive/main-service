package com.example.temp.baseball.dto;

import java.time.LocalDateTime;

public record FindGamesByTeamCommand(
        long teamId,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
