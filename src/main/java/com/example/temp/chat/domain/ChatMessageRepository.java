package com.example.temp.chat.domain;

import org.springframework.data.repository.Repository;

public interface ChatMessageRepository extends Repository<ChatMessage, Long> {

    void save(ChatMessage entity);
}
