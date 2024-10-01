package com.example.temp.chat.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends Repository<ChatMessage, Long> {

    void save(ChatMessage entity);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM chat_message cm " +
                    "WHERE cm.id < :lastId AND cm.room_id = :roomId " +
                    "ORDER BY cm.id DESC " +
                    "LIMIT :pageSize"
    )
    List<ChatMessage> findByPage(@Param("roomId") long roomId, @Param("lastId")long lastMessageId, @Param("pageSize")int pageSize);
}
