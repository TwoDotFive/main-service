package com.example.temp.user.domain;

import com.example.temp.common.exception.CustomException;
import com.example.temp.user.domain.value.PlatformType;
import org.springframework.data.repository.Repository;
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

    void delete(User user);
}
