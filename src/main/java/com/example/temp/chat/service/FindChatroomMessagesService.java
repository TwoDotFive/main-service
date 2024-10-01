package com.example.temp.chat.service;

import com.example.temp.chat.dto.ChatMessageView;

import java.util.List;

public interface FindChatroomMessagesService {

    List<ChatMessageView> doService(long roomId, long userId, long lastMessageId, int pageSize);
}
