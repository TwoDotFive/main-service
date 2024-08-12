package com.example.temp.fanpool.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindFanpoolBasedLocationCommand {
    private String eumpmyundongCd;
    private long gameId;
    private LocalDateTime departAt;
}
