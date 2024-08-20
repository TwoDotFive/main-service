package com.example.temp.tour.dto;

import java.util.List;

public record FindTourInformationResponse(
        int pageSize,
        int pageNumber,
        int totalCount,
        List<TourInformation> tourInformation
) {

    public static FindTourInformationResponse of(FindTourInformationByLocationHttpResponse response) {
        return new FindTourInformationResponse(
                response.getNumOfRows(),
                response.getPageNo(),
                response.getTotalCount(),
                extractTourInformation(response)
        );
    }

    private static List<TourInformation> extractTourInformation(FindTourInformationByLocationHttpResponse response) {
        return response.getItems()
                .stream()
                .map(item -> TourInformation.builder()
                        .addr1(item.getAddr1())
                        .addr2(item.getAddr2())
                        .cat1(item.getCat1())
                        .cat2(item.getCat2())
                        .cat3(item.getCat3())
                        .sigunguCode(item.getSigungucode())
                        .areaCode(item.getAreacode())
                        .contentTypeId(item.getContentTypeId())
                        // .createdTime(i.getCreatedTime())
                        // .modifiedTime(i.getModifiedTime())
                        .representativeImageUrl(item.getRepresentativeImageUrl())
                        .thumbnail(item.getThumbnail())
                        .x(item.getX())
                        .y(item.getY())
                        .tel(item.getTel())
                        .title(item.getTitle())
                        .dist(item.getDist())
                        .build()
                )
                .toList();
    }
}
