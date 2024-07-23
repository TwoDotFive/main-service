package com.example.temp.user.domain;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import com.example.temp.common.exception.CustomException;

public interface UserRepository extends Repository<Users, Long> {

	Optional<Users> findById(Long id);

	default Users findByIdOrElseThrow(Long id) {
		return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "회원 조회 실패"));
	}
}
