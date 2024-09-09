package com.example.temp.tour.dto;

import java.util.List;

public record FindTourInformationResponse(
        int pageSize,
        int pageNumber,
        int totalCount,
        List<TourPlaceInfo> items
) {

    public static FindTourInformationResponse of(FindTourInformationByLocationHttpResponse response) {
        return new FindTourInformationResponse(
                response.getNumOfRows(),
                response.getPageNo(),
                response.getTotalCount(),
                extractTourPlaceInformationItems(response)
        );
    }

    private static List<TourPlaceInfo> extractTourPlaceInformationItems(FindTourInformationByLocationHttpResponse response) {
        return response.getItems()
                .stream()
                .map(item -> TourPlaceInfo.builder()
                        .name(item.getTitle())
                        .thumbnail(item.getThumbnail())
                        .address(item.getAddr1())
                        .distance(item.getDist())
                        .contentId(item.getContentId())
                        .contentType(item.getContentTypeId())
                        .x(item.getX())
                        .y(item.getY())
                        .build()
                )
                .toList();
    }
}
