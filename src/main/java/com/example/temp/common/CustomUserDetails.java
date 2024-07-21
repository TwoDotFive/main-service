package com.example.temp.common;

import lombok.Getter;

@Getter
public class CustomUserDetails {

	private final Long id;

	public CustomUserDetails(Long userId) {
		this.id = userId;
	}
}
