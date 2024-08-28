package com.example.temp.tour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UpdateTourLogRequest(
        @JsonProperty("id") Long tourLogId,
        String title,
        List<TourScheduleView> schedules
) {
}
