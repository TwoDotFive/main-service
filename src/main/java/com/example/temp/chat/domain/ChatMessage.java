package com.example.temp.chat.domain;

import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    private Long id;

    private Long chatroomId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private ChatMessageType type;

    private String content;

    private LocalDateTime time;

    @Builder
    public ChatMessage(Long chatroomId, Long userId, ChatMessageType type, String content) {
        this.id = IdUtil.create();
        this.chatroomId = chatroomId;
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.time = LocalDateTime.now();
    }

    public ChatMessagePreview getPreview() {
        String contentPreview = switch (type) {
            case TEXT -> content;
            case IMAGE -> "이미지";
        };
        return new ChatMessagePreview(contentPreview, time);
    }

}
