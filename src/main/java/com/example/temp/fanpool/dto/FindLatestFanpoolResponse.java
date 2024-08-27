package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.Fanpool;

import java.util.List;

public record FindLatestFanpoolResponse(
        List<FanpoolInformationView> fanpoolInformation
) {
    public static FindLatestFanpoolResponse build(List<Fanpool> fanpools) {
        List<FanpoolInformationView> result = fanpools.stream()
                .map(FanpoolInformationView::new)
                .toList();
        return new FindLatestFanpoolResponse(result);
    }
}
