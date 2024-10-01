package com.example.temp.chat.dto;

public record CreateChatroomRequest(
        String guestId,
        String fanpoolId,
        ChatMessageDto message
) {

    public CreateChatroomCommand buildCommand(long userId) {
        return new CreateChatroomCommand(
                userId,
                Long.parseLong(guestId),
                Long.parseLong(fanpoolId),
                message
        );
    }
}
