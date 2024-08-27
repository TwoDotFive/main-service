package com.example.temp.fanpool.dto;

import org.springframework.data.domain.Pageable;

public record FindHostedFanpoolByUserCommand(
        long userId,
        Pageable pageable
) {
}
