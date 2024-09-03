package com.example.temp.tour.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.domain.TourLog;
import com.example.temp.tour.domain.TourLogRepository;
import com.example.temp.tour.service.DeleteTourLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTourLogServiceImpl implements DeleteTourLogService {

    private final TourLogRepository tourLogRepository;

    @Override
    @Transactional
    public void doService(Long userId, Long tourLogId) {
        TourLog tourLog = tourLogRepository.findByIdOrElseThrow(tourLogId);
        verifyPermission(userId, tourLog);
        tourLogRepository.delete(tourLog);
    }

    private void verifyPermission(Long userId, TourLog tourLog) {
        if (!tourLog.getUser().getId().equals(userId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Permission Denied");
        }
    }
}
