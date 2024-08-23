package com.example.temp.tour.dto;

public record TourScheduleDto(
        TourInformationSummary place,
        Integer day,
        Integer sequence,
        TourScheduleMemoDto memo
) {
}
