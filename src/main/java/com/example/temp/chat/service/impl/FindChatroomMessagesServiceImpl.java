package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.ChatMessageRepository;
import com.example.temp.chat.domain.ChatroomUserRepository;
import com.example.temp.chat.dto.ChatMessageView;
import com.example.temp.chat.service.FindChatroomMessagesService;
import com.example.temp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindChatroomMessagesServiceImpl implements FindChatroomMessagesService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomUserRepository chatroomUserRepository;

    @Override
    public List<ChatMessageView> doService(long roomId, long userId, long lastMessageId, int pageSize) {

        if (!chatroomUserRepository.existsByUserIdAndRoomId(userId, roomId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Illegal Request");
        }

        // 채팅방 접속 시, 마지막 활동 시간 갱신
        if (lastMessageId == Long.MAX_VALUE) {
            chatroomUserRepository.updateLastActivityTime(userId, roomId, LocalDateTime.now());
        }

        return chatMessageRepository.findByPage(roomId, lastMessageId, pageSize)
                .stream()
                .map(ChatMessageView::of)
                .toList();
    }
}
