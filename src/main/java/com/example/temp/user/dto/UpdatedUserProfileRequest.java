package com.example.temp.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdatedUserProfileRequest {
    private String nickname;
    private Long favoriteTeam;
    private String profileImageUrl;
    private String oneLiner; // 한 줄 소개
}
