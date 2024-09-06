package com.example.temp.tour.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindTourInformationDetailsResponse {

    private String contentId;
    private String contentTypeId;
    private String openTime;
    private String restDate;
    private String infoCenter;
    private String parking;

    private String firstMenu;
    private String treatMenu;

    private String fee;

    private String checkInTime;
    private String checkOutTime;
    private String reservationPageUrl;

    private String restroom;

    @Builder
    public FindTourInformationDetailsResponse(String contentId, String contentTypeId, String openTime, String restDate, String infoCenter, String parking, String firstMenu, String treatMenu, String fee, String checkInTime, String checkOutTime, String reservationPageUrl, String restroom) {
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
        this.openTime = openTime;
        this.restDate = restDate;
        this.infoCenter = infoCenter;
        this.parking = parking;
        this.firstMenu = firstMenu;
        this.treatMenu = treatMenu;
        this.fee = fee;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.reservationPageUrl = reservationPageUrl;
        this.restroom = restroom;
    }
}