package com.example.temp.tour.dto;

public record FindTourInformationByLocationCommand(
        int pageSize,
        int pageNumber,
        String x,
        String y,
        String radius,
        String contentTypeId
) {
}
