package com.example.temp.tour.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TourInformationBasedLocationDto {
    private int numOfRows;
    private int pageNo;
    private int totalCount;
    private List<Item> item;

    @Getter
    private static class Item {
        private String contentTypeId;
        private String areaCode;
        private String sigunguCode;

        private String cat1; // 대분류
        private String cat2; // 중분류
        private String cat3; // 소분류
        private String addr1;   // 주소 전체
        private String addr2; // 상세주소

        private String representativeImageUrl;  // 대표이미지
        private String thumbnail; // 썸네일 이미지
        private String x;
        private String y;

        private String dist; // 거리
        private String tel;
        private String title;

        private LocalDateTime createdTime;
        private LocalDateTime modifiedTime;

        @Builder
        public Item(String contentTypeId, String areaCode, String sigunguCode, String cat1, String cat2, String cat3, String addr1, String addr2, String representativeImageUrl, String thumbnail, String x, String y, String dist, String tel, String title, LocalDateTime createdTime, LocalDateTime modifiedTime) {
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

    public TourInformationBasedLocationDto(TourInformationBasedLocationResponse res) {
        this.numOfRows = res.getNumOfRows();
        this.pageNo = res.getPageNo();
        this.totalCount = res.getTotalCount();

        this.item = res.getItems().stream()
                .map(i -> Item.builder()
                        .addr1(i.getAddr1())
                        .addr2(i.getAddr2())
                        .cat1(i.getCat1())
                        .cat2(i.getCat2())
                        .cat3(i.getCat3())
                        .sigunguCode(i.getSigungucode())
                        .areaCode(i.getAreacode())
                        .contentTypeId(i.getContentTypeId())
                        .createdTime(i.getCreatedTime())
                        .modifiedTime(i.getModifiedTime())
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
