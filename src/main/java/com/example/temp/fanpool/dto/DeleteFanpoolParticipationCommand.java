package com.example.temp.fanpool.dto;

public record DeleteFanpoolParticipationCommand(
        long userId,
        long fanpoolId
) {
}
