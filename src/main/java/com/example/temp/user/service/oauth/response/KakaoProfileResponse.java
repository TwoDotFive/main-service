package com.example.temp.user.service.oauth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class KakaoProfileResponse {
    private Long id;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    public KakaoProfileResponse(Long id, KakaoAccount kakaoAccount) {
        this.id = id;
        this.kakaoAccount = kakaoAccount;
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class KakaoAccount {
        private String email;
        private KakaoProfile profile;

        public KakaoAccount(String email, KakaoProfile profile) {
            this.email = email;
            this.profile = profile;
        }

        @Getter
        @NoArgsConstructor
        @ToString
        public static class KakaoProfile {
            private String nickname;
            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl;

            public KakaoProfile(String nickname, String thumbnailImageUrl) {
                this.nickname = nickname;
                this.thumbnailImageUrl = thumbnailImageUrl;
            }
        }
    }
}