package com.example.temp.user.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface UserAuthenticatedLocationRepository extends Repository<UserAuthenticatedLocation, Long> {
    void save(UserAuthenticatedLocation entity);

    Optional<UserAuthenticatedLocation> findByUser(User user);

    default UserAuthenticatedLocation findByUserOrElseThrow(User user) {
        return findByUser(user).orElseThrow(() ->
                new CustomException(HttpStatus.BAD_REQUEST, "장소 인증을 먼저 진행해 주세요"));
    }
}
