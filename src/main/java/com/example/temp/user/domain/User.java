package com.example.temp.user.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.value.Nickname;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

	@Id
	private Long id;

	@Embedded
	private Nickname nickname;

	public static User build(Nickname nickname) {
		User user = new User();
		user.id = IdUtil.create();
		user.nickname = nickname;
		return user;
	}

	public String getNickname() {
		return nickname.getValue();
	}
}
