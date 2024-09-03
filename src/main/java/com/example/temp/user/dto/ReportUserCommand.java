package com.example.temp.user.dto;

public record ReportUserCommand(
        long userId,
        long targetUserId,
        String content
) {
}
