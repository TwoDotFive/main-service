package com.example.temp.tour.service.impl;

import com.example.temp.tour.domain.TourLogRepository;
import com.example.temp.tour.dto.TourLogPreview;
import com.example.temp.tour.service.FindRecentTourLogListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindRecentTourLogListServiceImpl implements FindRecentTourLogListService {

    private final TourLogRepository tourLogRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TourLogPreview> doService(long lastTourLogId, int pageSize) {
        return tourLogRepository.findRecentTourLogList(lastTourLogId, pageSize)
                .stream()
                .map(TourLogPreview::build)
                .toList();
    }
}
