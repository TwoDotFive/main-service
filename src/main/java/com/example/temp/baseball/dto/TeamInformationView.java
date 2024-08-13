package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Team;

public record TeamInformationView(
        long id,
        String name,
        String representativeImageUrl,
        String stadiumName,
        String stadiumAliasName
) {
    public TeamInformationView(Team team) {
        this(
                team.getId(),
                team.getName(),
                team.getRepresentativeImageUrl(),
                team.getStadium().getStadiumName(),
                team.getStadium().getAliasAddr()
        );
    }
}
