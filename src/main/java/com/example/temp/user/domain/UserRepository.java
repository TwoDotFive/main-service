package com.example.temp.user.domain;

import com.example.temp.baseball.domain.Team;
import com.example.temp.common.exception.CustomException;
import com.example.temp.user.domain.value.PlatformType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByPlatformTypeAndPlatformId(PlatformType platformType, Long platformId);

    User save(User saveUser);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "회원 조회 실패"));
    }

    @Query("SELECT u.favoriteTeam FROM User u WHERE u.id = :id")
    Team findFavoriteTeam(@Param("id") Long id);
}
