package com.example.temp.tour.dto;

import com.example.temp.tour.domain.TourScheduleMemo;

import java.util.List;

public record TourScheduleMemoView(
        String id,
        String content,
        List<TourScheduleMemoImageView> images
) {
    public static TourScheduleMemoView of(TourScheduleMemo memo) {
        List<TourScheduleMemoImageView> memoImageViews = memo.getImages()
                .stream()
                .map(TourScheduleMemoImageView::of)
                .toList();

        return new TourScheduleMemoView(
                memo.getId().toString(),
                memo.getContent(),
                memoImageViews
        );
    }
}
