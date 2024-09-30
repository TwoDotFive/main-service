package com.example.temp.user.dto;

public record VerifyCertificationCodeRequest(
        String phoneNumber,
        String code
) {
}
