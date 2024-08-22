package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Team;

public record TeamView(
        long id,
        String name,
        String representativeImageUrl,
        String stadiumName,
        String stadiumAliasName
) {
    public TeamView(Team team) {
        this(
                team.getId(),
                team.getName(),
                team.getRepresentativeImageUrl(),
                team.getStadium().getStadiumName(),
                team.getStadium().getAliasAddr()
        );
    }
}
