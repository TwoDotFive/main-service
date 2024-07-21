package com.example.temp.user.domain.value;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.example.temp.common.exception.CustomException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nickname {

	private static final String NICKNAME_REGEX = "^[a-zA-Z0-9가-힣]{2,12}$";

	private String nickname;

	@Getter
	@Column(name = "nickname_modified_at")
	private LocalDateTime modifiedAt;

	public Nickname(String nickname) {
		if (!nickname.matches(NICKNAME_REGEX)) {
			throw new CustomException(HttpStatus.BAD_REQUEST, "Invalid Nickname Format");
		}
		this.nickname = nickname;
		this.modifiedAt = LocalDateTime.now();
	}

	public String getValue() {
		return nickname;
	}

}
