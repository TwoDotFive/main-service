package com.example.temp.tour.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.domain.TourLog;
import com.example.temp.tour.domain.TourLogRepository;
import com.example.temp.tour.dto.TourLogView;
import com.example.temp.tour.service.FindTourLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindTourLogServiceImpl implements FindTourLogService {

    private final TourLogRepository tourLogRepository;

    @Override
    @Transactional(readOnly = true)
    public TourLogView doService(Long tourLogId) {
        TourLog tourLog = tourLogRepository.findByIdWithAllAssociationsForRead(tourLogId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Tour Log Id"));
        return TourLogView.of(tourLog);
    }
}
