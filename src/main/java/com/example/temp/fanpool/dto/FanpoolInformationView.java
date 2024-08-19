package com.example.temp.fanpool.dto;

import com.example.temp.baseball.dto.GameInformationView;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.geo.dto.AddressInformationView;

import java.time.LocalDateTime;


public record FanpoolInformationView(
        long id,
        long hostUserId,
        GameInformationView game,
        LocalDateTime departAt,
        AddressInformationView departFrom,
        String fanpoolType,
        String fanpoolTypeKor,
        String genderConstraint,
        int numberOfPeople,
        int currentNumberOfPeople
) {
    public FanpoolInformationView(Fanpool fanpool) {
        this(
                fanpool.getId(),
                fanpool.getHostUser().getId(),
                new GameInformationView(fanpool.getGame()),
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