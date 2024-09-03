package com.example.temp.baseball.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface StadiumRepository extends Repository<Stadium, Long> {

    Optional<Stadium> findById(Long id);

    List<Stadium> findAll();

    default Stadium findByOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Stadium Id"));
    }
}
