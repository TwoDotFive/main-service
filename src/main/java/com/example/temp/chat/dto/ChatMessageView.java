package com.example.temp.chat.dto;

import com.example.temp.chat.domain.ChatMessage;
import com.example.temp.chat.domain.ChatMessageType;

import java.time.LocalDateTime;

public record ChatMessageView(String id, ChatMessageType type, String content, LocalDateTime time) {

    public static ChatMessageView of(ChatMessage chatMessage) {
        return new ChatMessageView(
                chatMessage.getId().toString(),
                chatMessage.getType(),
                chatMessage.getContent(),
                chatMessage.getTime()
        );
    }
}
