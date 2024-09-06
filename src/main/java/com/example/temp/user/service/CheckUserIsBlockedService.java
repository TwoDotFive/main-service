package com.example.temp.user.service;

import com.example.temp.user.dto.CheckUserIsBlockedCommand;

public interface CheckUserIsBlockedService {
    void doService(long userId, long targetId);
}
