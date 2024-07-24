package com.example.temp.user.dto;

import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.User;

public record UserProfileView(String id, String nickname) {

	public UserProfileView(User user) {
		this(IdUtil.toString(user.getId()), user.getNickname());
	}
}
