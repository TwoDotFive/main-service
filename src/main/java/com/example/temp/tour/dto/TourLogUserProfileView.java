package com.example.temp.tour.dto;

import com.example.temp.user.domain.User;

public record TourLogUserProfileView(String nickname, String image) {

    public static TourLogUserProfileView of(User user) {
        return new TourLogUserProfileView(
                user.getNickname(),
                user.getProfileImageUrl()
        );
    }
}
