package com.example.temp.fanpool.dto;

import com.example.temp.baseball.dto.GameView;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.geo.dto.AddressInformationView;

import java.time.LocalDateTime;


public record FanpoolView(
        long id,
        long hostUserId,
        GameView game,
        String title,
        LocalDateTime departAt,
        AddressInformationView departFrom,
        String fanpoolType,
        String fanpoolTypeKor,
        String genderConstraint,
        int numberOfPeople,
        int currentNumberOfPeople
) {
    public FanpoolView(Fanpool fanpool) {
        this(
                fanpool.getId(),
                fanpool.getHostUser().getId(),
                new GameView(fanpool.getGame()),
                fanpool.getTitle(),
                fanpool.getDepartAt(),
                new AddressInformationView(fanpool.getDepartFrom()),
                fanpool.getFanpoolType().toString(),
                fanpool.getFanpoolType().getTypeKor(),
                fanpool.getGenderConstraint().getKor(),
                fanpool.getNumberOfPeople(),
                fanpool.getCurrentNumberOfPeople()
        );
    }
}