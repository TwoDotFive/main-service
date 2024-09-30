package com.example.temp.fanpool.dto.command;

import org.springframework.data.domain.Pageable;

import java.util.List;

public record FindFilteredFanpoolCommand(
        Long teamId,
        String dongCd,
        List<Long> gameId,
        boolean onlyGathering,
        Pageable pageable
) {
}
