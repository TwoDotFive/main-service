package com.example.temp.tour.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface TourLogStadiumRepository extends Repository<TourLogStadium, Long> {

    List<TourLogStadium> findAll();

    Optional<TourLogStadium> findById(Long id);

    default TourLogStadium findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Stadium Id"));
    }

    boolean existsById(Long id);
}
