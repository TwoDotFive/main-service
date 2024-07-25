package com.example.temp.user.controller;

import com.example.temp.common.CustomUserDetails;
import com.example.temp.user.service.impl.OAuthService;
import com.example.temp.user.service.oauth.response.OAuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class OAuthController {
    private final OAuthService oAuthService;

    @GetMapping("/{platform}/login")
    public ResponseEntity<String> loginform(
            @PathVariable("platform") String platform,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        String loginPage = oAuthService.loginPage(platform);
        return ResponseEntity.ok(loginPage);
    }

    @GetMapping("/{platform}/login/redirect")
    public ResponseEntity<OAuthResponse> login(
            @PathVariable("platform") String platform,
            @RequestParam("code") String code
    ) {
        OAuthResponse profileResponse = oAuthService.login(platform, code);
        return ResponseEntity.ok(profileResponse);
    }
}
