package com.example.temp.user.dto;

public record ReportUserRequest(
        String targetUserId,
        String content
) {
}
