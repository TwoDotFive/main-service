package com.example.temp.user.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.user.dto.UpdatedUserProfileRequest;
import com.example.temp.user.dto.UserAuthenticatedLocationRequest;
import com.example.temp.user.dto.UserProfileView;
import com.example.temp.user.service.FindUserProfileService;
import com.example.temp.user.service.SaveUserAuthenticatedLocationService;
import com.example.temp.user.service.UpdateUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final FindUserProfileService findUserProfileService;
    private final UpdateUserProfileService updateUserProfileService;
    private final SaveUserAuthenticatedLocationService saveUserAuthenticatedLocationService;

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

    @PostMapping("/location")
    public ResponseEntity<Void> updateAuthenticatedLocation(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @RequestBody UserAuthenticatedLocationRequest locationRequest
    ) {
        saveUserAuthenticatedLocationService.doService(authenticatedUser.getId(), locationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
