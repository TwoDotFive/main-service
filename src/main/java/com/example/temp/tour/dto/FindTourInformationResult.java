package com.example.temp.tour.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FindTourInformationResult {

    private final int numOfRows;
    private final int pageNo;
    private final int totalCount;
    private final List<TourInformation> tourInformation;

    public FindTourInformationResult(FindTourInformationByLocationHttpResponse response) {
        this.numOfRows = response.getNumOfRows();
        this.pageNo = response.getPageNo();
        this.totalCount = response.getTotalCount();

        this.tourInformation = response.getItems().stream()
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
