package com.example.temp.chat.service;

import com.example.temp.chat.dto.FindChatroomListResult;

import java.util.List;

public interface FindChatroomListService {

    List<FindChatroomListResult> doService(long userId);
}
