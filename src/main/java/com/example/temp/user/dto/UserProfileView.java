package com.example.temp.user.dto;

import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.Users;

public record UserProfileView(String id, String nickname) {

	public UserProfileView(Users users) {
		this(IdUtil.toString(users.getId()), users.getNickname());
	}
}
