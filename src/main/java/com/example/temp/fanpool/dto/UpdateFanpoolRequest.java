package com.example.temp.fanpool.dto;

import com.example.temp.geo.dto.AddressInformationView;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UpdateFanpoolRequest {
    private String title;
    private String gameId;
    private LocalDateTime departAt;
    private AddressInformationView departFrom;
    private String fanpoolType;
    private String genderConstraint;
    private int numberOfPeople;
    private int currentOfPeople;
    private String memo;
}
