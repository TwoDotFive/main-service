package com.example.temp.chat.service;

import com.example.temp.chat.dto.ChatMessageDto;

public interface SendChatMessageService {

    void doService(long senderId, long chatroomId, ChatMessageDto message);
}
