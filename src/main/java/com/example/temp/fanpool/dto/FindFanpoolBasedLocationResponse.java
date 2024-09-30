package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.Fanpool;
import lombok.Getter;

@Getter
public class FindFanpoolBasedLocationResponse {
    private FanpoolView fanpoolInformation;
    private boolean hosted;

    public static FindFanpoolBasedLocationResponse build(Fanpool fanpool, long userId) {
        FindFanpoolBasedLocationResponse ret = new FindFanpoolBasedLocationResponse();
        ret.fanpoolInformation = new FanpoolView(fanpool);
        ret.hosted = !fanpool.isNotHostUser(userId);         // 내가 주최한 팬풀인지 확인
        return ret;
    }
}
