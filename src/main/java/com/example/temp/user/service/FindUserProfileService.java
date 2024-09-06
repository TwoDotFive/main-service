package com.example.temp.user.service;

import com.example.temp.user.dto.FindUserProfileCommand;
import com.example.temp.user.dto.UserProfileView;

public interface FindUserProfileService {

    UserProfileView doService(FindUserProfileCommand command);
}
