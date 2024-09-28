package com.example.temp.tour.service;

import com.example.temp.tour.dto.TourLogPreview;

import java.util.List;

public interface FindTourLogListByUserService {

    List<TourLogPreview> doService(long userId, long lastTourLogId, int pageSize);
}
