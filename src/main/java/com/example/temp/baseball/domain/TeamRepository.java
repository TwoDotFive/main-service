package com.example.temp.baseball.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface TeamRepository extends Repository<Team, Long> {
    Team findById(long id);

    List<Team> findBySeason(Season season);

    default Team findByIdOrElseThrow(long teamId) {
        Team ret = findById(teamId);
        if (ret == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Team Not Found");
        }
        return ret;
    }
}
