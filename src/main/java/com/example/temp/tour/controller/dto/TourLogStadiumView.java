package com.example.temp.tour.controller.dto;

import com.example.temp.tour.domain.TourLogStadium;

public record TourLogStadiumView(
        String id,
        String address,
        String contentType,
        String contentId,
        String name,
        String shortenName,
        String thumbnail,
        String x,
        String y
) {

    public static TourLogStadiumView of(TourLogStadium tourLogStadium) {
        return new TourLogStadiumView(
                tourLogStadium.getId().toString(),
                tourLogStadium.getAddress(),
                tourLogStadium.getContentTypeId(),
                tourLogStadium.getContentId(),
                tourLogStadium.getName(),
                tourLogStadium.getShortenName(),
                tourLogStadium.getImage(),
                tourLogStadium.getX(),
                tourLogStadium.getY()
        );
    }
}
