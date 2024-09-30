package com.example.temp.tour.dto;

import lombok.Builder;

@Builder
public record TourPlaceInfo(
        String name,
        String address,
        String thumbnail,
        String distance,
        String contentId,
        String contentType,
        String x,
        String y
) {
}
