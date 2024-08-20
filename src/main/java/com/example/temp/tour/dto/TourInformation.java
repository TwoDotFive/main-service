package com.example.temp.tour.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TourInformation(
        String contentTypeId,
        String areaCode,
        String sigunguCode,
        String cat1,
        String cat2,
        String cat3,
        String addr1,
        String addr2,
        String representativeImageUrl,
        String thumbnail,
        String x,
        String y,
        String dist,
        String tel,
        String title,
        LocalDateTime createdTime,
        LocalDateTime modifiedTime
) {
}
