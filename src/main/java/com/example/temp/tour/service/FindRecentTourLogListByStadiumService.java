package com.example.temp.tour.service;

import com.example.temp.tour.dto.TourLogPreview;

import java.util.List;

public interface FindRecentTourLogListByStadiumService {

    List<TourLogPreview> doService(long stadiumId, long lastTourLogId, int pageSize);
}
