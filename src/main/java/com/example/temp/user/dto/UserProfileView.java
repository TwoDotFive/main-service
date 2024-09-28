package com.example.temp.user.dto;

import com.example.temp.baseball.dto.TeamView;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.value.UserRole;

public record UserProfileView(
        String id,
        String email,
        String nickname,
        String profileImageUrl,
        String name,
        String oneLiner,
        UserRole userRole,
        TeamView favoriteTeam,
        Long hostedFanpoolNumber,
        Long hostedTourLogNumber
) {
    public UserProfileView(User user, Long hostedFanpoolNumber, Long hostedTourLogNumber) {
        this(
                user.getId().toString(),
                user.getEmail(),
                user.getNickname(),
                user.getProfileImageUrl(),
                user.getName(),
                user.getOneLiner(),
                user.getUserRole(),
                new TeamView(user.getFavoriteTeam()),
                hostedFanpoolNumber,
                hostedTourLogNumber
        );
    }
}
