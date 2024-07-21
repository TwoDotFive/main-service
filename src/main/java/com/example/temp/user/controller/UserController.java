package com.example.temp.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.temp.common.CustomUserDetails;
import com.example.temp.user.dto.UserProfileView;
import com.example.temp.user.service.FindUserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final FindUserProfileService findUserProfileService;

	@GetMapping("/profile")
	public ResponseEntity<UserProfileView> findProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
		UserProfileView response = findUserProfileService.doService(userDetails.getId());
		return ResponseEntity.ok(response);
	}

}
