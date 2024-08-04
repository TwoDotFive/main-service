package com.example.temp.chat.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserChatRoom> userChatRooms = new HashSet<>();

    private String lastMessage;

    public static ChatRoom create() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.id = IdUtil.create();
        return chatRoom;
    }

    public void addUserChatRoom(UserChatRoom userChatRoom) {
        userChatRooms.add(userChatRoom);
    }

}
