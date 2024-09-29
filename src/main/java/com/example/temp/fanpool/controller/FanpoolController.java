package com.example.temp.fanpool.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.fanpool.dto.*;
import com.example.temp.fanpool.dto.command.*;
import com.example.temp.fanpool.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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
    private final FindHostedFanpoolByUserService findHostedFanpoolByUserService;
    private final FindLatestFanpoolService findLatestFanpoolService;
    private final FindFanpoolByIdService findFanpoolByIdService;
    private final FindFilteredFanpoolService findFilteredFanpoolService;
    private final UpdateFanpoolService updateFanpoolService;
    private final UpdateFanpoolStateService updateFanpoolStateService;
    private final ParticipateFanpoolService participateFanpoolService;
    private final DeleteFanpoolParticipationService deleteFanpoolParticipationService;

    @GetMapping
    public ResponseEntity<FindHostedFanpoolByUserResponse> getAllByUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 10, page = 0) Pageable pageable,
            @RequestParam("userId") String userId
    ) {
        FindHostedFanpoolByUserResponse result = findHostedFanpoolByUserService
                .doService(new FindHostedFanpoolByUserCommand(Long.parseLong(userId), pageable));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/latest")
    public ResponseEntity<FindLatestFanpoolResponse> getLatest(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        FindLatestFanpoolResponse result = findLatestFanpoolService.doService(customUserDetails.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<FindFilteredFanpoolResponse> getFiltered(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 10, page = 0) Pageable pageable,
            @RequestParam(value = "teamId", required = false) long teamId,
            @RequestParam(value = "dongCd", required = false) String dongCd,
            @RequestParam(value = "gameId", required = false) List<Long> gameId,
            @RequestParam(value = "onlyGathering", required = true) boolean onlyGathering,

            @RequestParam(value = "departAt", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime departAt
    ) {
        FindFilteredFanpoolCommand command = new FindFilteredFanpoolCommand(teamId, dongCd, gameId, departAt, onlyGathering, pageable);
        FindFilteredFanpoolResponse result = findFilteredFanpoolService.doService(command);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{fanpoolId}")
    public ResponseEntity<FindFanpoolBasedLocationResponse> get(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") String fanpoolId
    ) {
        FindFanpoolBasedLocationResponse result = findFanpoolByIdService.doService(Long.parseLong(fanpoolId));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<FanpoolView> save(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody CreateFanpoolRequest request
    ) {
        FanpoolView result = createFanpoolService.doService(userDetails.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{fanpoolId}")
    public ResponseEntity<Void> gather(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") String fanpoolId
    ) {
        participateFanpoolService.doService(new ParticipateFanpoolCommand(customUserDetails.getId(), Long.parseLong(fanpoolId)));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{fanpoolId}")
    public ResponseEntity<Void> updateInfo(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") String fanpoolId,
            @RequestBody UpdateFanpoolRequest request
    ) {
        UpdateFanpoolCommand command = new UpdateFanpoolCommand(customUserDetails.getId(), Long.parseLong(fanpoolId), request);
        updateFanpoolService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{fanpoolId}/state")
    public ResponseEntity<Void> updateState(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") String fanpoolId,
            @RequestBody UpdateFanpoolStateRequest request
    ) {
        UpdateFanpoolStateCommand command = new UpdateFanpoolStateCommand(customUserDetails.getId(), Long.parseLong(fanpoolId), request);
        updateFanpoolStateService.doService(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{fanpoolId}/cancel")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fanpoolId") String fanpoolId
    ) {
        deleteFanpoolParticipationService
                .doService(new DeleteFanpoolParticipationCommand(customUserDetails.getId(), Long.parseLong(fanpoolId)));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
