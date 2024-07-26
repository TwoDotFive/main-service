package com.example.temp.user.controller;

import com.example.temp.common.CustomUserDetails;
import com.example.temp.user.dto.AuthLoginResponse;
import com.example.temp.user.service.CreateUserService;
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
    private final CreateUserService createUserService;

    @GetMapping("/{platformType}/login")
    public ResponseEntity<String> loginform(
            @PathVariable("platformType") String platformType,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        String loginPage = oAuthService.loginPage(platformType);
        return ResponseEntity.ok(loginPage);
    }

    @GetMapping("/{platformType}/login/redirect")
    public ResponseEntity<AuthLoginResponse> login(
            @PathVariable("platformType") String platformType,
            @RequestParam("code") String code
    ) {
        OAuthResponse profileResponse = oAuthService.login(platformType, code);
        AuthLoginResponse authLoginResponse = createUserService.doService(profileResponse);
        return ResponseEntity.ok(authLoginResponse);
    }
}
