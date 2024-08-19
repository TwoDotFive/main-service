package com.example.temp.fanpool.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FanpoolType {
    CAR_SHARE("차량공유"),
    TAXI_PARTY("택시팟");
    private final String typeKor;
}
