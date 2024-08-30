package com.example.temp.user.dto;

public record ReportUserRequest(
        long targetUserId,
        String content
) {
}
