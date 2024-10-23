package com.example.temp.user.service.impl;

import com.example.temp.user.domain.User;
import com.example.temp.user.dto.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenService {

    private final SecretKey secretKey;
    private final long expirationTime;

    private static final String ROLE_CLAIM = "role";

    @Autowired
    public JwtTokenService(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.expiration-time}") long expirationTime
    ) {
        this.secretKey = new SecretKeySpec(Base64.getDecoder().decode(secretKey), "HmacSHA256");
        this.expirationTime = expirationTime;
    }

    public JwtToken createJwtToken(User user) {
        // Access Token 생성
        String accessToken = generateAccessToken(user);
        return new JwtToken(accessToken);
    }

    public String generateAccessToken(User user) {
        ClaimsBuilder claimsBuilder = Jwts.claims();
        claimsBuilder.subject(String.valueOf(user.getId()));
        claimsBuilder.add(ROLE_CLAIM, user.getUserRole().name());
        Claims claims = claimsBuilder.build();
        return generateToken(claims, new Date(System.currentTimeMillis() + expirationTime));
    }

    public String generateToken(Claims claims, Date expiredTime) {
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiredTime)
                .signWith(secretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
