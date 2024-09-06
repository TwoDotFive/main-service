package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.Fanpool;

import java.util.List;

public record FindHostedFanpoolByUserResponse(
        List<FanpoolView> fanpoolInformation
) {
    public static FindHostedFanpoolByUserResponse build(List<Fanpool> fanpools) {
        List<FanpoolView> result = fanpools.stream()
                .map(FanpoolView::new)
                .toList();
        return new FindHostedFanpoolByUserResponse(result);
    }
}
