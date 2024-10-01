package com.example.temp.chat.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessagePreview {

    private String content;

    private LocalDateTime time;

    public ChatMessagePreview(String content, LocalDateTime time) {
        this.content = content;
        this.time = time;
    }
}
