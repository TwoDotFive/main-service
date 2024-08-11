package com.example.temp.tour.dto;

public record FindTourInformationByLocationCommand(
        int numOfRows,
        int pageNo,
        String x,
        String y,
        String radius,
        String contentTypeId
) {
}
