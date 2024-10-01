package com.example.temp.chat.dto;

public record SendChatMessageRequest(
        String roomId,
        ChatMessageDto message
) {
}
