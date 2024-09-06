package com.example.temp.tour.service;

import com.example.temp.tour.dto.BookmarkedTourLogPreview;

import java.util.List;

public interface FindUserBookmarkedTourLogListService {

    List<BookmarkedTourLogPreview> doService(Long userId, Long lastBookmarkId, int pageSize);
}
