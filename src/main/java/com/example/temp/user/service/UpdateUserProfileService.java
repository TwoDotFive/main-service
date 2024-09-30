package com.example.temp.user.service;

import com.example.temp.baseball.dto.UpdatedUserProfileCommand;

public interface UpdateUserProfileService {

    void doService(long userId, UpdatedUserProfileCommand command);
}
