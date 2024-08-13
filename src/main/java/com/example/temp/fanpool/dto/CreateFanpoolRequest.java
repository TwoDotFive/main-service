package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.value.FanpoolType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CreateFanpoolRequest {
    private LocalDateTime departAt;
    private Long gameId;
    private Integer numberOfPeople;
    private String memo;
    private FanpoolType fanpoolType;
    private Address departFrom;

    @Getter
    @NoArgsConstructor
    private static class Address {
        private String fullText;
        private String zipNo;
        private String sido;
        private String sigungu;
        private String dong;
        private String dongCd;
        private String x;
        private String y;
    }
}
