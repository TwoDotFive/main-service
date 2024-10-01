package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.*;
import com.example.temp.chat.dto.CreateChatroomCommand;
import com.example.temp.chat.service.CreateChatroomService;
import com.example.temp.fanpool.domain.FanpoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateChatroomServiceImpl implements CreateChatroomService {

    private final FanpoolRepository fanpoolRepository;
    private final ChatroomRepository chatroomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomUserRepository chatroomUserRepository;

    @Override
    @Transactional
    public Long doService(CreateChatroomCommand command) {

        Chatroom chatroom = createChatroom(command);
        ChatMessage chatMessage = createChatMessage(command, chatroom);
        ChatroomUser hostChatroom = createChatroomUser(chatroom, command.hostUserId(), LocalDateTime.of(2024, 10, 1, 0, 0));
        ChatroomUser guestChatroom = createChatroomUser(chatroom, command.guestUserId(), chatMessage.getTime());

        chatroom.updateLastMessage(chatMessage.getPreview());

        chatroomRepository.save(chatroom);
        chatMessageRepository.save(chatMessage);
        chatroomUserRepository.save(hostChatroom);
        chatroomUserRepository.save(guestChatroom);

        return chatroom.getId();
    }

    private Chatroom createChatroom(CreateChatroomCommand command) {

        String teams = fanpoolRepository.findTeamNamesById(command.fanpoolId());

        return Chatroom.builder()
                .hostUserId(command.hostUserId())
                .guestUserId(command.guestUserId())
                .fanpoolId(command.fanpoolId())
                .teams(teams)
                .build();
    }

    private ChatMessage createChatMessage(CreateChatroomCommand command, Chatroom chatroom) {
        return ChatMessage.builder()
                .roomId(chatroom.getId())
                .userId(command.hostUserId())
                .type(command.message().type())
                .content(command.message().content())
                .build();
    }

    private ChatroomUser createChatroomUser(Chatroom chatroom, long userId, LocalDateTime lastActivityTime) {
        return ChatroomUser.builder()
                .roomId(chatroom.getId())
                .userId(userId)
                .lastActivityTime(lastActivityTime)
                .build();
    }
}
