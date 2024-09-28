package com.example.temp.tour.service;

import com.example.temp.tour.dto.TourLogPreview;

import java.util.List;

public interface FindTourLogListByTourPlaceService {

    List<TourLogPreview> doService(int contentId, int contentTypeId);
}
