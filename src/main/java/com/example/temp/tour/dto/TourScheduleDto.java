package com.example.temp.tour.dto;

public record TourScheduleDto(
        TourPlaceView place,
        Integer day,
        Integer sequence,
        TourScheduleMemoDto memo
) {
}
