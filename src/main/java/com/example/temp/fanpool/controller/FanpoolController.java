package com.example.temp.fanpool.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.fanpool.dto.*;
import com.example.temp.fanpool.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/fanpool")
@RequiredArgsConstructor
public class FanpoolController {
    private final CreateFanpoolService createFanpoolService;
    private final FindLatestFanpoolService findLatestFanpoolService;
    private final FindFanpoolByIdService findFanpoolByIdService;
    private final FindFilteredFanpoolService findFilteredFanpoolService;
    private final UpdateFanpoolService updateFanpoolService;
    private final UpdateFanpoolStateService updateFanpoolStateService;

    @GetMapping
    public ResponseEntity<FindLatestFanpoolResponse> getLatest(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        FindLatestFanpoolResponse result = findLatestFanpoolService.doService(customUserDetails.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<FindFilteredFanpoolResponse> getAllFanpool(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 10, page = 0) Pageable pageable,
            @RequestParam(value = "teamId", required = true) long teamId,
            @RequestParam(value = "dongCd", required = false) String dongCd,
            @RequestParam(value = "gameId", required = false) List<Long> gameId,
            @RequestParam(value = "departAt", required = false) LocalDateTime departAt,
            @RequestParam(value = "onlyGathering", required = false) boolean onlyGathering
    ) {
        FindFilteredFanpoolCommand command = new FindFilteredFanpoolCommand(teamId, dongCd, gameId, departAt, onlyGathering, pageable);
        FindFilteredFanpoolResponse result = findFilteredFanpoolService.doService(customUserDetails.getId(), command);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{fanpoolId}")
    public ResponseEntity<FindFanpoolBasedLocationResponse> getFanpool(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") long fanpoolId
    ) {
        FindFanpoolBasedLocationResponse result = findFanpoolByIdService.doService(fanpoolId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<FanpoolInformationView> save(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody CreateFanpoolRequest request
    ) {
        FanpoolInformationView result = createFanpoolService.doService(userDetails.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{fanpoolId}")
    public ResponseEntity<Void> updateInfo(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") long fanpoolId,
            @RequestBody UpdateFanpoolRequest request
    ) {
        UpdateFanpoolCommand command = new UpdateFanpoolCommand(customUserDetails.getId(), fanpoolId, request);
        updateFanpoolService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{fanpoolId}/state")
    public ResponseEntity<Void> updateInfo(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") long fanpoolId,
            @RequestBody UpdateFanpoolStateRequest request
    ) {
        UpdateFanpoolStateCommand command = new UpdateFanpoolStateCommand(customUserDetails.getId(), fanpoolId, request);
        updateFanpoolStateService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
