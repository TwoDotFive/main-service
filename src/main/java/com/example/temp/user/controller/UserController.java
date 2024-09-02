package com.example.temp.user.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.user.dto.*;
import com.example.temp.user.service.*;
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
    private final BlockUserService blockUserService;
    private final ReportUserService reportUserService;
    private final DeleteUserBlockingService deleteUserBlockingService;

    @GetMapping("/profile/{targetUserId}")
    public ResponseEntity<UserProfileView> findProfile(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @PathVariable("targetUserId") long targetUserId
    ) {
        FindUserProfileCommand command = new FindUserProfileCommand(authenticatedUser.getId(), targetUserId);
        UserProfileView response = findUserProfileService.doService(command);
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

    @PostMapping("/block")
    public ResponseEntity<Void> block(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody BlockUserRequest request
    ) {
        BlockUserCommand command = new BlockUserCommand(customUserDetails.getId(), request.targetUserId());
        blockUserService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/report")
    public ResponseEntity<Void> report(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody ReportUserRequest request
    ) {
        ReportUserCommand command = new ReportUserCommand(customUserDetails.getId(), request.targetUserId(), request.content());
        reportUserService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/block")
    public ResponseEntity<Void> deletBlocking(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "targetUserId", required = true) long targetUserId
    ) {
        DeleteUserBlockingCommand command = new DeleteUserBlockingCommand(customUserDetails.getId(), targetUserId);
        deleteUserBlockingService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
