package com.example.temp.user.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface UserAuthenticatedLocationRepository extends Repository<UserAuthenticatedLocation, Long> {
    UserAuthenticatedLocation save(UserAuthenticatedLocation entity);

    List<UserAuthenticatedLocation> findByUser(User user);

    default List<UserAuthenticatedLocation> findByUserOrElseThrow(User user) {
        List<UserAuthenticatedLocation> result = findByUser(user);
        if (result.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "장소 인증을 먼저 진행해 주세요");
        }
        return result;
    }

    Optional<UserAuthenticatedLocation> findById(Long id);

    default UserAuthenticatedLocation findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.BAD_REQUEST, "Authenticated Location not Found"));
    }

    @Query("SELECT f FROM UserAuthenticatedLocation f WHERE f.user = :user AND f.representative is TRUE ")
    Optional<UserAuthenticatedLocation> findByUserAndRepresentative(@Param("user") User user);
}
