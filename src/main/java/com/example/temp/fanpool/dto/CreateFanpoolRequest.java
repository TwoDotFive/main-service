package com.example.temp.fanpool.dto;

import com.example.temp.fanpool.domain.value.FanpoolType;
import com.example.temp.fanpool.domain.value.GenderConstraint;
import com.example.temp.geo.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CreateFanpoolRequest {
    private String title;
    private LocalDateTime departAt;
    private Long gameId;
    private Integer numberOfPeople;
    private String memo;
    private FanpoolType fanpoolType;
    private GenderConstraint genderConstraint;
    private DepartFrom departFrom;

    @Getter
    @NoArgsConstructor
    private static class DepartFrom {
        private String fullText;
        private String zipNo;
        private String sido;
        private String sigungu;
        private String dong;
        private String dongCd;
        private String x;
        private String y;
    }

    public Address toAddressEntity() {
        return Address.builder()
                .fullText(this.departFrom.fullText)
                .zipNo(this.departFrom.zipNo)
                .sido(this.departFrom.sido)
                .sigungu(this.departFrom.sigungu)
                .dong(this.departFrom.dong)
                .x(this.departFrom.x)
                .y(this.departFrom.y)
                .dongCd(this.departFrom.dongCd)
                .build();
    }
}
