package com.example.temp.user.controller;

import com.example.temp.user.dto.BriefUserProfileDto;
import com.example.temp.user.service.FindBriefUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class AdminUserController {

    private final FindBriefUserProfileService findBriefUserProfileService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<BriefUserProfileDto> findProfile(@PathVariable(name = "userId") long userId) {
        BriefUserProfileDto response = findBriefUserProfileService.doService(userId);
        return ResponseEntity.ok(response);
    }
}
