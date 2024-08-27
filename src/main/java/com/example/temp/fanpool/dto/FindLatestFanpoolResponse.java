package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.Fanpool;

import java.util.List;

public record FindLatestFanpoolResponse(
        List<FanpoolView> fanpoolInformation
) {
    public static FindLatestFanpoolResponse build(List<Fanpool> fanpools) {
        List<FanpoolView> result = fanpools.stream()
                .map(FanpoolView::new)
                .toList();
        return new FindLatestFanpoolResponse(result);
    }
}
