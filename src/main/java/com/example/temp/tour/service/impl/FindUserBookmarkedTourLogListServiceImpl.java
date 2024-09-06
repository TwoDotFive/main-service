package com.example.temp.tour.service.impl;

import com.example.temp.tour.domain.TourLogBookmarkRepository;
import com.example.temp.tour.dto.BookmarkedTourLogPreview;
import com.example.temp.tour.service.FindUserBookmarkedTourLogListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindUserBookmarkedTourLogListServiceImpl implements FindUserBookmarkedTourLogListService {

    private final TourLogBookmarkRepository tourLogBookmarkRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookmarkedTourLogPreview> doService(Long userId, Long lastBookmarkId, int pageSize) {
        return tourLogBookmarkRepository.findUserBookmarkedTourLogPreviewList(userId, lastBookmarkId, pageSize)
                .stream()
                .map(BookmarkedTourLogPreview::build)
                .toList();
    }
}
