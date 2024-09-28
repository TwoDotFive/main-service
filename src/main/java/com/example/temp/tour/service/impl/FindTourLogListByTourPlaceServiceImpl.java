package com.example.temp.tour.service.impl;

import com.example.temp.tour.domain.TourLogRepository;
import com.example.temp.tour.dto.TourLogPreview;
import com.example.temp.tour.service.FindTourLogListByTourPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTourLogListByTourPlaceServiceImpl implements FindTourLogListByTourPlaceService {

    private final TourLogRepository tourLogRepository;

    @Override
    public List<TourLogPreview> doService(int contentId, int contentTypeId) {
        return tourLogRepository.findByTourPlace(contentId, contentTypeId)
                .stream()
                .map(TourLogPreview::build)
                .toList();
    }
}
