package com.example.temp.fanpool.dto;

import com.example.temp.baseball.dto.GameInformationView;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.geo.dto.AddressInformationView;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FanpoolInformationView {
    private long id;
    private long hostUserId;
    private GameInformationView game;
    private LocalDateTime departAt;
    private AddressInformationView departFrom;
    private int numberOfPeople;
    private int currentNumberOfPeople;

    public FanpoolInformationView(Fanpool fanpool) {
        this.id = fanpool.getId();
        hostUserId = fanpool.getHostUser().getId();
        game = new GameInformationView(fanpool.getGame());
        departFrom = new AddressInformationView(fanpool.getDepartFrom());
        departAt = fanpool.getDepartAt();
        numberOfPeople = fanpool.getNumberOfPeople();
        currentNumberOfPeople = fanpool.getCurrentNumberOfPeople();
    }
}
