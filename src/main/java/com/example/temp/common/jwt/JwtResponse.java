package com.example.temp.common.jwt;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record JwtResponse(String accessToken, String refreshToken) {

}
