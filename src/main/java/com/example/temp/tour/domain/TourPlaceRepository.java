package com.example.temp.tour.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface TourPlaceRepository extends Repository<TourPlace, Long> {

    TourPlace save(TourPlace entity);

    Optional<TourPlace> findByContentIdAndContentTypeId(Integer contentId, Integer contentTypeId);

}
