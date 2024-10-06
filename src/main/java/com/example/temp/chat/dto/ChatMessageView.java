package com.example.temp.chat.dto;

import com.example.temp.chat.domain.ChatMessage;
import com.example.temp.chat.domain.ChatMessageType;

import java.time.LocalDateTime;

public record ChatMessageView(String id, String senderId, ChatMessageType type, String content, LocalDateTime time) {

    public static ChatMessageView of(ChatMessage chatMessage) {
        return new ChatMessageView(
                String.valueOf(chatMessage.getId()),
                String.valueOf(chatMessage.getUserId()),
                chatMessage.getType(),
                chatMessage.getContent(),
                chatMessage.getTime()
        );
    }
}
