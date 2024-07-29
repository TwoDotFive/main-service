package com.example.temp.user.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.user.dto.UserProfileView;
import com.example.temp.user.service.FindUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final FindUserProfileService findUserProfileService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileView> findProfile(@AuthenticationPrincipal CustomUserDetails authenticatedUser) {
        UserProfileView response = findUserProfileService.doService(authenticatedUser.getId());
        return ResponseEntity.ok(response);
    }

}
