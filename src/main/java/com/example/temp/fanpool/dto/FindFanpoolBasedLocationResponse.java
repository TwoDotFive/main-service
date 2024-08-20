package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.Fanpool;
import lombok.Getter;

@Getter
public class FindFanpoolBasedLocationResponse {
    private FanpoolInformationView fanpoolInformation;

    public static FindFanpoolBasedLocationResponse build(Fanpool fanpool) {
        FindFanpoolBasedLocationResponse ret = new FindFanpoolBasedLocationResponse();
        ret.fanpoolInformation = new FanpoolInformationView(fanpool);
        return ret;
    }
}
