package com.example.temp.tour.dto;

import com.example.temp.tour.domain.TourSchedule;

public record TourScheduleView(
        String id,
        TourPlaceView place,
        int day,
        int sequence,
        TourScheduleMemoView memo
) {
    public static TourScheduleView of(TourSchedule tourSchedule) {
        return new TourScheduleView(
                tourSchedule.getId().toString(),
                TourPlaceView.of(tourSchedule.getTourPlace()),
                tourSchedule.getDay(),
                tourSchedule.getSequence(),
                TourScheduleMemoView.of(tourSchedule.getMemo())
        );
    }
}
