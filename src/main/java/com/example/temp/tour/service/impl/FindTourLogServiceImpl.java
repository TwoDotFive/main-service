package com.example.temp.tour.service.impl;

import com.example.temp.tour.domain.TourLog;
import com.example.temp.tour.domain.TourLogRepository;
import com.example.temp.tour.dto.TourLogView;
import com.example.temp.tour.service.FindTourLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindTourLogServiceImpl implements FindTourLogService {

    private final TourLogRepository tourLogRepository;

    @Override
    @Transactional(readOnly = true)
    public TourLogView doService(Long tourLogId) {
        TourLog tourLog = tourLogRepository.findByIdOrElseThrow(tourLogId);
        return TourLogView.of(tourLog);
    }
}
