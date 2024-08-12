package com.example.temp.fanpool.dto;

import com.example.temp.baseball.dto.GameInformationView;
import com.example.temp.fanpool.domain.Fanpool;
import lombok.Getter;

@Getter
public class FindFanpoolBasedLocationResponse {
    private GameInformationView gameInformation;
    private FanpoolInformationView fanpoolInformation;

    public static FindFanpoolBasedLocationResponse build(Fanpool fanpool) {
        FindFanpoolBasedLocationResponse ret = new FindFanpoolBasedLocationResponse();
        ret.fanpoolInformation = new FanpoolInformationView(fanpool);
        ret.gameInformation = new GameInformationView(fanpool.getGame());
        return ret;
    }
}
