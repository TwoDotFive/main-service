package com.example.temp.tour.service.impl;

import com.example.temp.tour.domain.TourLogBookmark;
import com.example.temp.tour.domain.TourLogBookmarkRepository;
import com.example.temp.tour.service.FindTourLogBookmarkIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindTourLogBookmarkIdServiceImpl implements FindTourLogBookmarkIdService {

    private final TourLogBookmarkRepository tourLogBookmarkRepository;

    @Override
    @Transactional(readOnly = true)
    public Long doService(Long userId, Long tourLogId) {
        TourLogBookmark tourLogBookmark = tourLogBookmarkRepository.findByUserIdAndTourLogIdOrElseThrow(userId, tourLogId);
        return tourLogBookmark.getId();
    }
}
