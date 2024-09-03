package com.example.temp.tour.service;

import com.example.temp.tour.dto.TourLogPreview;

import java.util.List;

public interface FindRecentTourLogListService {

    List<TourLogPreview> doService(long lastTourLogId, int pageSize);
}
