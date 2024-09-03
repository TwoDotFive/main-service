package com.example.temp.tour.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.domain.TourLogBookmark;
import com.example.temp.tour.domain.TourLogBookmarkRepository;
import com.example.temp.tour.domain.TourLogRepository;
import com.example.temp.tour.service.RegisterTourLogBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterTourLogBookmarkServiceImpl implements RegisterTourLogBookmarkService {

    private final TourLogRepository tourLogRepository;
    private final TourLogBookmarkRepository tourLogBookmarkRepository;

    @Override
    @Transactional
    public Long doService(Long userId, Long tourLogId) {
        if (!tourLogRepository.existsById(tourLogId)) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Invalid Tour Log Id");
        }
        if (tourLogBookmarkRepository.existsByUserIdAndTourLogId(userId, tourLogId)) {
            throw new CustomException(HttpStatus.CONFLICT, "The Bookmark Already Exists");
        }
        TourLogBookmark tourLogBookmark = new TourLogBookmark(userId, tourLogId);
        tourLogBookmarkRepository.save(tourLogBookmark);
        return tourLogBookmark.getId();
    }
}
