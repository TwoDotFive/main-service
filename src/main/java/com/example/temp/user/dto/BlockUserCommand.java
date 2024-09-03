package com.example.temp.user.dto;

public record BlockUserCommand(
        long userId,
        long targetUserId
) {
}
