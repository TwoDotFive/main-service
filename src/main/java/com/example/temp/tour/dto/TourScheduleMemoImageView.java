package com.example.temp.tour.dto;

import com.example.temp.tour.domain.TourScheduleMemoImage;

public record TourScheduleMemoImageView(
        String id,
        String url,
        Integer sequence
) {
    public static TourScheduleMemoImageView of(TourScheduleMemoImage memoImage) {
        return new TourScheduleMemoImageView(
                memoImage.getId().toString(),
                memoImage.getImageUrl(),
                memoImage.getSequence()
        );
    }
}
