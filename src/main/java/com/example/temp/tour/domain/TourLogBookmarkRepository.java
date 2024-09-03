package com.example.temp.tour.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface TourLogBookmarkRepository extends Repository<TourLogBookmark, Long> {

    void save(TourLogBookmark entity);

    Optional<TourLogBookmark> findById(Long id);

    default TourLogBookmark findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Bookmark Id"));
    }

    boolean existsByUserIdAndTourLogId(Long userId, Long tourLogId);

    void delete(TourLogBookmark entity);
}
