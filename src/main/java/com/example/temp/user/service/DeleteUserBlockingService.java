package com.example.temp.user.service;

import com.example.temp.user.dto.DeleteUserBlockingCommand;

public interface DeleteUserBlockingService {
    void doService(DeleteUserBlockingCommand command);
}
