package com.example.temp.user.controller;

import com.example.temp.user.dto.AuthLoginResponse;
import com.example.temp.user.dto.SendCertificationCodeRequest;
import com.example.temp.user.dto.VerifyCertificationCodeRequest;
import com.example.temp.user.service.CreateUserService;
import com.example.temp.user.service.SendCertificationCodeService;
import com.example.temp.user.service.VerifyCertificationCodeService;
import com.example.temp.user.service.impl.OAuthService;
import com.example.temp.user.service.oauth.response.OAuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class OAuthController {
    private final OAuthService oAuthService;
    private final CreateUserService createUserService;
    private final SendCertificationCodeService sendCertificationCodeService;
    private final VerifyCertificationCodeService verifyCertificationCodeService;

    @GetMapping("/{platformType}/login")
    public ResponseEntity<String> loginform(
            @PathVariable("platformType") String platformType
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

        HttpStatus status = (authLoginResponse.isFirstLogin()) ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(authLoginResponse);
    }

    @PostMapping("/phone")
    public ResponseEntity<Void> sendCertificationCode(
            @RequestBody SendCertificationCodeRequest request
    ) {
        sendCertificationCodeService.doService(request.phoneNumber());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/phone/verify")
    public ResponseEntity<Void> verifyCode(
            @RequestBody VerifyCertificationCodeRequest request
    ) {
        verifyCertificationCodeService.doService(request.code(), request.phoneNumber());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
