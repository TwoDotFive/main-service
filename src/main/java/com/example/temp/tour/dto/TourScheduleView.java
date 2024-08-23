package com.example.temp.tour.dto;

import com.example.temp.tour.domain.TourSchedule;

public record TourScheduleView(
        String id,
        String name,
        String address,
        String thumbnail,
        int day,
        int sequence,
        TourScheduleMemoView memo
) {
    public static TourScheduleView of(TourSchedule tourSchedule) {
        return new TourScheduleView(
                tourSchedule.getId().toString(),
                tourSchedule.getTourPlace().getName(),
                tourSchedule.getTourPlace().getAddress(),
                tourSchedule.getTourPlace().getThumbnail(),
                tourSchedule.getDay(),
                tourSchedule.getSequence(),
                TourScheduleMemoView.of(tourSchedule.getMemo())
        );
    }
}
