package com.example.temp.chat.dto;

import lombok.Builder;

@Builder
public record UserProfileView(String id, String nickname, String image) {
}
