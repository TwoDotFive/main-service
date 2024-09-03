package com.example.temp.tour.dto;

public record BookmarkedTourLogPreview(
        String id,
        TourLogPreview tourLog
) {
    public static BookmarkedTourLogPreview build(BookmarkedTourLogPreviewNativeDto vo) {
        TourLogPreview tourLogPreview = new TourLogPreview(
                vo.getTourLogId(),
                vo.getImage(),
                vo.getTitle(),
                vo.getStadiumName(),
                new UserProfileView(vo.getUserNickname(), vo.getUserProfileImage()));

        return new BookmarkedTourLogPreview(vo.getBookmarkId(), tourLogPreview);
    }
}
