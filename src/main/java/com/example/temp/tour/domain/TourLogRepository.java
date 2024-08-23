package com.example.temp.tour.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface TourLogRepository extends Repository<TourLog, Long> {

    TourLog save(TourLog entity);

    Optional<TourLog> findById(Long id);

    default TourLog findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Tour Log Id"));
    }
}
