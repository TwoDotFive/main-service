package com.example.temp.tour.service.impl;

import com.example.temp.tour.domain.TourLogRepository;
import com.example.temp.tour.dto.TourLogPreview;
import com.example.temp.tour.service.FindTourLogListByUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTourLogListByUserServiceImpl implements FindTourLogListByUserService {

    private final TourLogRepository tourLogRepository;

    @Override
    public List<TourLogPreview> doService(long userId, long lastTourLogId, int pageSize) {
        return tourLogRepository.findByUser(userId, lastTourLogId, pageSize)
                .stream()
                .map(TourLogPreview::build)
                .toList();
    }
}
