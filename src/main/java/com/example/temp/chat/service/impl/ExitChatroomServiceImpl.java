package com.example.temp.chat.service.impl;

import com.example.temp.chat.domain.ChatroomUserRepository;
import com.example.temp.chat.service.ExitChatroomService;
import com.example.temp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExitChatroomServiceImpl implements ExitChatroomService {

    private final ChatroomUserRepository chatroomUserRepository;

    @Override
    @Transactional
    public void doService(long userId, long roomId) {

        if (!chatroomUserRepository.existsByUserIdAndRoomId(userId, roomId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Illegal Request");
        }

        chatroomUserRepository.deleteByUserIdAndRoomId(userId, roomId);
    }
}
