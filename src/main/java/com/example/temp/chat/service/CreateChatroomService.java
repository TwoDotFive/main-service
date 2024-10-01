package com.example.temp.chat.service;

import com.example.temp.chat.dto.CreateChatroomCommand;

public interface CreateChatroomService {

    Long doService(CreateChatroomCommand command);
}
