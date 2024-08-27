package com.example.temp.fanpool.controller;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.fanpool.dto.*;
import com.example.temp.fanpool.service.CreateFanpoolService;
import com.example.temp.fanpool.service.FindFanpoolByIdService;
import com.example.temp.fanpool.service.FindFilteredFanpoolService;
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
    private final FindFanpoolByIdService findFanpoolByIdService;
    private final FindFilteredFanpoolService findFilteredFanpoolService;

    @GetMapping("/filter")
    public ResponseEntity<FindFilteredFanpoolResponse> getAllFanpool(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 10, page = 0) Pageable pageable,
            @RequestParam(value = "teamId", required = true) long teamId,
            @RequestParam(value = "dongCd", required = false) String dongCd,
            @RequestParam(value = "gameId", required = false) List<Long> gameId,
            @RequestParam(value = "departAt", required = false) LocalDateTime departAt
    ) {
        FindFilteredFanpoolCommand command = new FindFilteredFanpoolCommand(teamId, dongCd, gameId, departAt, pageable);
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
}
