package com.example.temp.chat.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface ChatroomRepository extends Repository<Chatroom, Long> {

    void save(Chatroom entity);

    Optional<Chatroom> findById(Long id);

    default Chatroom findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Chatroom Id"));
    }
}
