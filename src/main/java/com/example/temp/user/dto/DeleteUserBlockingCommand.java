package com.example.temp.user.dto;

public record DeleteUserBlockingCommand(
        long userId,
        long targetUserId
) {
}
