package com.example.temp.fanpool.dto.command;

public record DeleteFanpoolParticipationCommand(
        long userId,
        long fanpoolId
) {
}
