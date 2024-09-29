package com.example.temp.baseball.dto;

import com.example.temp.user.dto.UpdatedUserProfileRequest;

public record UpdatedUserProfileCommand(
        long id,
        String nickname,
        long favoriteTeam,
        String profileImageUrl,
        String oneLiner
) {

    public UpdatedUserProfileCommand(long id, UpdatedUserProfileRequest userProfile) {
        this(
                id,
                userProfile.getNickname(),
                Long.parseLong(userProfile.getFavoriteTeam()),
                userProfile.getProfileImageUrl(),
                userProfile.getOneLiner()
        );
    }
}
