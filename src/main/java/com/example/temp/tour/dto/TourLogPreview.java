package com.example.temp.tour.dto;

public record TourLogPreview(
        String id,
        String image,
        String title,
        String stadiumName,
        UserProfileView profile
) {

    public static TourLogPreview build(TourLogPreviewNativeDto vo) {
        return new TourLogPreview(
                vo.getId(),
                vo.getImage(),
                vo.getTitle(),
                vo.getStadiumName(),
                new UserProfileView(vo.getUserNickname(), vo.getUserProfileImage()));
    }
}
