package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.Fanpool;
import lombok.Getter;

import java.util.List;

@Getter
public class FindFilteredFanpoolResponse {
    private final List<FanpoolView> fanpools;

    public static FindFilteredFanpoolResponse build(List<Fanpool> fanpools) {
        List<FanpoolView> result = fanpools.stream()
                .map(FanpoolView::new)
                .toList();
        return new FindFilteredFanpoolResponse(result);
    }

    private FindFilteredFanpoolResponse(List<FanpoolView> fanpools) {
        this.fanpools = fanpools;
    }
}
