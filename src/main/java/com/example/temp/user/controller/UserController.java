package com.example.temp.user.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.user.dto.UpdatedUserProfileRequest;
import com.example.temp.user.dto.UserProfileView;
import com.example.temp.user.service.FindUserProfileService;
import com.example.temp.user.service.UpdateUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final FindUserProfileService findUserProfileService;
    private final UpdateUserProfileService updateUserProfileService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileView> findProfile(@AuthenticationPrincipal CustomUserDetails authenticatedUser) {
        UserProfileView response = findUserProfileService.doService(authenticatedUser.getId());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserProfileView> updateProfile(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @RequestBody UpdatedUserProfileRequest userProfile
    ) {
        UserProfileView response = updateUserProfileService.doService(authenticatedUser.getId(), userProfile);
        return ResponseEntity.ok(response);
    }

}
