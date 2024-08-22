package com.example.temp.tour.dto;

import lombok.Builder;

@Builder
public record TourInformationSummary(
        String title,
        String address,
        String thumbnail,
        String distance,
        String contentId,
        String contentType
) {
}
