package com.example.temp.baseball.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface TeamRepository extends Repository<Team, Long> {
    Team findById(long id);

    List<Team> findBySeason(Season season);
}
