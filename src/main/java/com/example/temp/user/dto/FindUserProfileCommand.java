package com.example.temp.user.dto;

public record FindUserProfileCommand(
        long userId,
        long targetUserId
) {
}
