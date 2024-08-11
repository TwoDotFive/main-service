package com.example.temp.tour.dto;

public record TourInformationBasedLocationCommand(
        int numOfRows,
        int pageNo,
        String x,
        String y,
        String radius,
        String contentTypeId
) {
}
