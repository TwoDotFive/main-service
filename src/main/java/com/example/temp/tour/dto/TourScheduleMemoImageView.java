package com.example.temp.tour.dto;

import com.example.temp.tour.domain.TourScheduleMemoImage;

public record TourScheduleMemoImageView(String url, Integer sequence) {

    public static TourScheduleMemoImageView of(TourScheduleMemoImage memoImage) {
        return new TourScheduleMemoImageView(
                memoImage.getImageUrl(),
                memoImage.getSequence()
        );
    }
}
