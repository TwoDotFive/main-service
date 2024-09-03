package com.example.temp.tour.dto;

import com.example.temp.tour.domain.TourPlace;
import lombok.Builder;

@Builder
public record TourPlaceView(
        String id,
        String name,
        String address,
        String thumbnail,
        String contentId,
        String contentType,
        String x,
        String y
) {

    public static TourPlaceView of(TourPlace tourPlace) {
        return new TourPlaceView(
                tourPlace.getId().toString(),
                tourPlace.getName(),
                tourPlace.getAddress(),
                tourPlace.getThumbnail(),
                String.valueOf(tourPlace.getContentId()),
                String.valueOf(tourPlace.getContentTypeId()),
                String.valueOf(tourPlace.getX()),
                String.valueOf(tourPlace.getY())
        );
    }
}
