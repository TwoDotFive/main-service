package com.example.temp.user.controller;

import com.example.temp.baseball.dto.UpdatedUserProfileCommand;
import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.user.dto.*;
import com.example.temp.user.service.*;
import com.example.temp.user.service.dto.FindUserAuthenticatedLocationResponse;
import com.example.temp.user.service.location.DeleteUserAuthenticatedLocationService;
import com.example.temp.user.service.location.FindUserAuthenticatedLocationService;
import com.example.temp.user.service.location.UpdateUserAuthenticatedLocationService;
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
    private final FindUserAuthenticatedLocationService findUserAuthenticatedLocationService;
    private final UpdateUserAuthenticatedLocationService updateUserAuthenticatedLocationService;
    private final DeleteUserAuthenticatedLocationService deleteUserAuthenticatedLocationService;


    @GetMapping("/profile/{targetUserId}")
    public ResponseEntity<UserProfileView> findProfile(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @PathVariable("targetUserId") String targetUserId
    ) {
        long longTargetUserId = Long.parseLong(targetUserId);
        if (longTargetUserId == 0) longTargetUserId = authenticatedUser.getId();
        FindUserProfileCommand command = new FindUserProfileCommand(authenticatedUser.getId(), longTargetUserId);
        UserProfileView response = findUserProfileService.doService(command);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/profile")
    public ResponseEntity<Void> updateProfile(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @RequestBody UpdatedUserProfileRequest userProfile
    ) {
        UpdatedUserProfileCommand command = new UpdatedUserProfileCommand(authenticatedUser.getId(), userProfile);
        updateUserProfileService.doService(authenticatedUser.getId(), command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/location")
    public ResponseEntity<FindUserAuthenticatedLocationResponse> findUserLocation(
            @AuthenticationPrincipal CustomUserDetails authenticatedUser,
            @RequestParam(value = "userId") Long userId
    ) {
        if (userId == 0L) {
            userId = authenticatedUser.getId();
        }

        FindUserAuthenticatedLocationResponse response = findUserAuthenticatedLocationService.doService(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/location")
    public ResponseEntity<Void> saveAuthenticatedLocation(
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
        BlockUserCommand command = new BlockUserCommand(customUserDetails.getId(), Long.parseLong(request.targetUserId()));
        blockUserService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/report")
    public ResponseEntity<Void> report(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody ReportUserRequest request
    ) {
        ReportUserCommand command =
                new ReportUserCommand(customUserDetails.getId(), Long.parseLong(request.targetUserId()), request.content());
        reportUserService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/block")
    public ResponseEntity<Void> deleteBlocking(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "targetUserId") String targetUserId
    ) {
        DeleteUserBlockingCommand command = new DeleteUserBlockingCommand(customUserDetails.getId(), Long.parseLong(targetUserId));
        deleteUserBlockingService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PatchMapping("/location/{locationId}")
    public ResponseEntity<Void> updateAuthenticatedLocation(
            @PathVariable("locationId") String locationId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        updateUserAuthenticatedLocationService.doService(customUserDetails.getId(), Long.parseLong(locationId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/location/{locationId}")
    public ResponseEntity<Void> deleteAuthenticatedLocation(
            @PathVariable("locationId") String locationId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        deleteUserAuthenticatedLocationService.doService(customUserDetails.getId(), Long.parseLong(locationId));
        return ResponseEntity.ok().build();
    }

}
