package com.example.temp.tour.dto;

public record FindTourInformationDetailsCommand(
        int pageSize,
        int pageNo,
        String contentId,
        String contentTypeId
) {
}
