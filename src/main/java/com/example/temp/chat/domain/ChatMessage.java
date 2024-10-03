package com.example.temp.chat.domain;

import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
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
public class ChatMessage implements Persistable<Long> {

    @Id
    private Long id;

    private Long roomId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private ChatMessageType type;

    private String content;

    @CreatedDate
    private LocalDateTime time;

    @Builder
    public ChatMessage(Long roomId, Long userId, ChatMessageType type, String content) {
        this.id = IdUtil.create();
        this.roomId = roomId;
        this.userId = userId;
        this.type = type;
        this.content = content;
    }

    public ChatMessagePreview getPreview() {
        String contentPreview = switch (type) {
            case TEXT -> content;
            case IMAGE -> "이미지";
        };
        return new ChatMessagePreview(contentPreview, time);
    }

    @Override
    public boolean isNew() {
        return time == null;
    }
}
