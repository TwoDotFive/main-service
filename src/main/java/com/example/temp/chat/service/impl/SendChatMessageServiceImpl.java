package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.*;
import com.example.temp.chat.dto.ChatMessageDto;
import com.example.temp.chat.service.SendChatMessageService;
import com.example.temp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendChatMessageServiceImpl implements SendChatMessageService {

    private final ChatroomRepository chatroomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomUserRepository chatroomUserRepository;

    @Override
    @Transactional
    public void doService(long senderId, long chatroomId, ChatMessageDto message) {

        Chatroom chatroom = chatroomRepository.findByIdOrElseThrow(chatroomId);

        verifyPermission(senderId, chatroom);

        ChatMessage chatMessage = saveChatMessage(senderId, chatroomId, message);

        chatroom.updateLastMessage(chatMessage.getPreview());

        chatroomUserRepository.updateLastActivityTime(senderId, chatroomId, chatMessage.getTime());
    }

    private void verifyPermission(long senderId, Chatroom chatroom) {
        if (!chatroom.getHostUserId().equals(senderId) && !chatroom.getGuestUserId().equals(senderId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Illegal Request");
        }
    }

    public ChatMessage saveChatMessage(long senderId, long chatroomId, ChatMessageDto message) {

        ChatMessage chatMessage = ChatMessage.builder()
                .content(message.content())
                .type(message.type())
                .userId(senderId)
                .chatroomId(chatroomId)
                .build();

        chatMessageRepository.save(chatMessage);

        return chatMessage;
    }
}
