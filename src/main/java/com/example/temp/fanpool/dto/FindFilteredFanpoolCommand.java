package com.example.temp.fanpool.dto;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public record FindFilteredFanpoolCommand(
        Long teamId,
        String dongCd,
        List<Long> gameId,
        LocalDateTime departAt,
        boolean onlyGathering,
        Pageable pageable
) {
}
