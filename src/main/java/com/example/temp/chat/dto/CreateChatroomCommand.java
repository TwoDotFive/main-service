package com.example.temp.chat.dto;

public record CreateChatroomCommand(
        long hostUserId,
        long guestUserId,
        long fanpoolId,
        ChatMessageDto message
) {
}
