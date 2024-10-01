package com.example.temp.chat.dto;

import com.example.temp.chat.domain.ChatMessageType;

public record ChatMessageDto(ChatMessageType type, String content) {
}
