package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Team;

public record TeamView(
        String id,
        String name,
        String representativeImageUrl,
        String stadiumName,
        String stadiumAliasName
) {
    public TeamView(Team team) {
        this(
                team.getId().toString(),
                team.getName(),
                team.getRepresentativeImageUrl(),
                team.getStadium().getStadiumName(),
                team.getStadium().getShortenName()
        );
    }
}
