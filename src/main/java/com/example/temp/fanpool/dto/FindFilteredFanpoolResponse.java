package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.Fanpool;
import lombok.Getter;

import java.util.List;

@Getter
public class FindFilteredFanpoolResponse {
    private final List<FanpoolInformationView> result;

    public static FindFilteredFanpoolResponse build(List<Fanpool> fanpools) {
        List<FanpoolInformationView> result = fanpools.stream()
                .map(FanpoolInformationView::new)
                .toList();
        return new FindFilteredFanpoolResponse(result);
    }

    private FindFilteredFanpoolResponse(List<FanpoolInformationView> result) {
        this.result = result;
    }
}
