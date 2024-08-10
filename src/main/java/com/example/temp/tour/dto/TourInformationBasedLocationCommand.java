package com.example.temp.tour.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TourInformationBasedLocationCommand {
    private int numOfRows;
    private int pageNo;
    private String x;
    private String y;
    private String radius;
    private String contentTypeId;

    public static TourInformationBasedLocationCommand build(int numOfRows, int pageNo, String x, String y, String radius, String contentTypeId) {
        TourInformationBasedLocationCommand ret = new TourInformationBasedLocationCommand();
        ret.numOfRows = numOfRows;
        ret.pageNo = pageNo;
        ret.x = x;
        ret.y = y;
        ret.radius = radius;
        ret.contentTypeId = contentTypeId;
        return ret;
    }
}
