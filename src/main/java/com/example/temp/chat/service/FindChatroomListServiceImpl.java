package com.example.temp.chat.service;

import com.example.temp.chat.domain.ChatroomRepository;
import com.example.temp.chat.dto.FindChatroomListResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindChatroomListServiceImpl implements FindChatroomListService {

    private final ChatroomRepository chatroomRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FindChatroomListResult> doService(long userId) {
        return chatroomRepository.findUserJoinedChatroomList(userId)
                .stream()
                .map(FindChatroomListResult::of)
                .toList();
    }
}
