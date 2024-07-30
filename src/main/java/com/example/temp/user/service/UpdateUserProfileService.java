package com.example.temp.user.service;

import com.example.temp.user.dto.UpdatedUserProfileRequest;
import com.example.temp.user.dto.UserProfileView;

public interface UpdateUserProfileService {
    public UserProfileView doService(long userId, UpdatedUserProfileRequest updatedUserProfileRequest);
}
