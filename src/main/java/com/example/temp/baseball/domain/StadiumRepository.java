package com.example.temp.baseball.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface StadiumRepository extends Repository<Stadium, Long> {

    List<Stadium> findAll();
}
