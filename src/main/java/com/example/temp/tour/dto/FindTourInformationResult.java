package com.example.temp.tour.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FindTourInformationResult {

    private final int numOfRows;
    private final int pageNo;
    private final int totalCount;
    private final List<TourInformation> tourInformation;

    @Getter
    private static class TourInformation {
        private final String contentTypeId;
        private final String areaCode;
        private final String sigunguCode;

        private final String cat1; // 대분류
        private final String cat2; // 중분류
        private final String cat3; // 소분류
        private final String addr1;   // 주소 전체
        private final String addr2; // 상세주소

        private final String representativeImageUrl;  // 대표이미지
        private final String thumbnail; // 썸네일 이미지
        private final String x;
        private final String y;

        private final String dist; // 거리
        private final String tel;
        private final String title;

        private final LocalDateTime createdTime;
        private final LocalDateTime modifiedTime;

        @Builder
        public TourInformation(String contentTypeId, String areaCode, String sigunguCode, String cat1, String cat2, String cat3, String addr1, String addr2, String representativeImageUrl, String thumbnail, String x, String y, String dist, String tel, String title, LocalDateTime createdTime, LocalDateTime modifiedTime) {
            this.contentTypeId = contentTypeId;
            this.areaCode = areaCode;
            this.sigunguCode = sigunguCode;
            this.cat1 = cat1;
            this.cat2 = cat2;
            this.cat3 = cat3;
            this.addr1 = addr1;
            this.addr2 = addr2;
            this.representativeImageUrl = representativeImageUrl;
            this.thumbnail = thumbnail;
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.tel = tel;
            this.title = title;
            this.createdTime = createdTime;
            this.modifiedTime = modifiedTime;
        }
    }

    public FindTourInformationResult(FindTourInformationByLocationHttpResponse res) {
        this.numOfRows = res.getNumOfRows();
        this.pageNo = res.getPageNo();
        this.totalCount = res.getTotalCount();

        this.tourInformation = res.getItems().stream()
                .map(i -> TourInformation.builder()
                        .addr1(i.getAddr1())
                        .addr2(i.getAddr2())
                        .cat1(i.getCat1())
                        .cat2(i.getCat2())
                        .cat3(i.getCat3())
                        .sigunguCode(i.getSigungucode())
                        .areaCode(i.getAreacode())
                        .contentTypeId(i.getContentTypeId())
                        // .createdTime(i.getCreatedTime())
                        // .modifiedTime(i.getModifiedTime())
                        .representativeImageUrl(i.getRepresentativeImageUrl())
                        .thumbnail(i.getThumbnail())
                        .x(i.getX())
                        .y(i.getY())
                        .tel(i.getTel())
                        .title(i.getTitle())
                        .dist(i.getDist())
                        .build()
                ).toList();

    }
}
