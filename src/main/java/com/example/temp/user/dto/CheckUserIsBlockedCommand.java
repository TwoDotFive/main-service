package com.example.temp.user.dto;

public record CheckUserIsBlockedCommand(
        long userId,
        long targetUserId
) {
}
