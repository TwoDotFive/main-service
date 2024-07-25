package com.example.temp.user.domain;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import com.example.temp.common.exception.CustomException;

public interface UserRepository extends Repository<User, Long> {

	Optional<User> findById(Long id);
	Optional<User> findByEmail(String email);
	User save(User saveUser);

	default User findByIdOrElseThrow(Long id) {
		return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "회원 조회 실패"));
	}
}
