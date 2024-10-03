package com.example.temp.chat.domain;

import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatroomUser implements Persistable<Long> {

    @Id
    private Long id;

    private Long roomId;

    private Long userId;

    @CreatedDate
    private LocalDateTime createdTime;

    private LocalDateTime lastActivityTime;

    @Builder
    public ChatroomUser(Long roomId, Long userId, LocalDateTime lastActivityTime) {
        this.id = IdUtil.create();
        this.roomId = roomId;
        this.userId = userId;
        this.lastActivityTime = lastActivityTime;
    }

    @Override
    public boolean isNew() {
        return createdTime == null;
    }
}
