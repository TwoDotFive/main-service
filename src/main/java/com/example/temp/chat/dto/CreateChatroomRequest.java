package com.example.temp.chat.dto;

public record CreateChatroomRequest(
        String hostUserId,
        String fanpoolId,
        ChatMessageDto message
) {

    public CreateChatroomCommand buildCommand(long userId) {
        return new CreateChatroomCommand(
                Long.parseLong(hostUserId),
                userId,
                Long.parseLong(fanpoolId),
                message
        );
    }
}
