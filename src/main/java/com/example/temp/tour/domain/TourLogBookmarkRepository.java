package com.example.temp.tour.domain;

import org.springframework.data.repository.Repository;

public interface TourLogBookmarkRepository extends Repository<TourLogBookmark, Long> {

    void save(TourLogBookmark entity);

    boolean existsByUserIdAndTourLogId(Long userId, Long tourLogId);
}
