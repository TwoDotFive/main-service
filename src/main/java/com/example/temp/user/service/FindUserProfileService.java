package com.example.temp.user.service;

import com.example.temp.user.dto.UserProfileView;

public interface FindUserProfileService {

	UserProfileView doService(String email);
}
