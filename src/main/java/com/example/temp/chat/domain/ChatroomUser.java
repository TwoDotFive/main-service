package com.example.temp.chat.domain;

import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatroomUser {

    @Id
    private Long id;

    private Long roomId;

    private Long userId;

    private LocalDateTime lastActivityTime;

    @Builder
    public ChatroomUser(Long roomId, Long userId, LocalDateTime lastActivityTime) {
        this.id = IdUtil.create();
        this.roomId = roomId;
        this.userId = userId;
        this.lastActivityTime = lastActivityTime;
    }
}
