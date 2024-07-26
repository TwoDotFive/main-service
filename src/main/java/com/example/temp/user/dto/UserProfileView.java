package com.example.temp.user.dto;

import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRole;

public record UserProfileView(String email, String nickname, String profileImageUrl, String name, UserRole userRole) {

    public UserProfileView(User user) {
        this(user.getEmail(), user.getNickname(), user.getProfileImageUrl(), user.getName(), user.getUserRole());
    }
}
