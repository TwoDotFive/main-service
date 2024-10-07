package com.example.temp.chat.dto;

import java.time.LocalDateTime;

public interface FindChatroomListNativeDto {

    Long getRoomId();

    Long getFanpoolId();

    Integer getIsHost();

    LocalDateTime getLastActivityTime();

    Long getPartnerId();

    String getPartnerNickname();

    String getPartnerImage();

    String getTeams();

    String getLastMessageContent();

    LocalDateTime getLastMessageTime();
}
