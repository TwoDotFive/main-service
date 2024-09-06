package com.example.temp.tour.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.domain.TourLogBookmark;
import com.example.temp.tour.domain.TourLogBookmarkRepository;
import com.example.temp.tour.service.DeleteTourLogBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTourLogBookmarkServiceImpl implements DeleteTourLogBookmarkService {

    private final TourLogBookmarkRepository tourLogBookmarkRepository;

    @Override
    @Transactional
    public void doService(Long userId, Long tourLogId) {
        TourLogBookmark bookmark = tourLogBookmarkRepository.findByUserIdAndTourLogIdOrElseThrow(userId, tourLogId);
        if (!bookmark.getUserId().equals(userId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Illegal Request");
        }
        tourLogBookmarkRepository.delete(bookmark);
    }
}
