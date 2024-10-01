package com.example.temp.chat.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatroom extends BaseTimeEntity {

    @Id
    private Long id;

    private Long fanpoolId;

    private Long hostUserId;

    private Long guestUserId;

    // 팬풀 관련 경기 참여 팀 이름 (ex : 키움히어로즈 vs KIA타이거즈)
    private String teams;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "content", column = @Column(name = "last_message_content")),
            @AttributeOverride(name = "time", column = @Column(name = "last_message_time"))
    })
    private ChatMessagePreview lastMessage;

    @Builder
    public Chatroom(Long fanpoolId, Long hostUserId, Long guestUserId, String teams) {
        this.id = IdUtil.create();
        this.fanpoolId = fanpoolId;
        this.hostUserId = hostUserId;
        this.guestUserId = guestUserId;
        this.teams = teams;
    }

    public void updateLastMessage(ChatMessagePreview message) {
        this.lastMessage = message;
    }
}
