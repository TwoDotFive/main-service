package com.example.temp.fanpool.dto;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Getter
public class FindFanpoolBasedLocationCommand {
    private String dongCd;
    private long gameId;
    private LocalDateTime departAt;

    private Pageable pageable;

    public FindFanpoolBasedLocationCommand(String dongCd, long gameId, LocalDateTime departAt, Pageable pageable) {
        this.dongCd = dongCd;
        this.gameId = gameId;
        this.departAt = departAt;
        this.pageable = pageable;
    }
}
