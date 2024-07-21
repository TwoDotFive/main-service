package com.example.temp.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.UserProfileView;
import com.example.temp.user.service.FindUserProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindUserProfileServiceImpl implements FindUserProfileService {

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserProfileView doService(Long userId) {
		User user = userRepository.findByIdOrElseThrow(userId);
		return new UserProfileView(user);
	}
}
