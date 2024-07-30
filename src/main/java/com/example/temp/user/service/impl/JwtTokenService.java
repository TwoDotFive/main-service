package com.example.temp.user.service.impl;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.user.domain.User;
import com.example.temp.user.dto.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private static final String IDENTIFICATION_CLAIM = "identification";
    private static final String ROLE_CLAIM = "role";
    private static final String NAME_CLAIM = "name";
    private static final String PROFILE_IMAGE_CLAIM = "profileImageUrl";

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expiration-time}")
    private long expirationTime;

    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String token) {
        Claims claims = extractAllClaims(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.get(IDENTIFICATION_CLAIM, Long.class).toString());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public JwtToken createJwtToken(User user) {
        // JWT 토큰 생성을 위한 claims 생성
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(IDENTIFICATION_CLAIM, user.getId());
        claims.put(ROLE_CLAIM, user.getUserRole().name());
        claims.put(NAME_CLAIM, user.getName());
        claims.put(PROFILE_IMAGE_CLAIM, user.getProfileImageUrl());

        UserDetails customUserDetails = CustomUserDetails.create(user);
        // Access Token 생성
        final String accessToken = generateAccessToken(claims, customUserDetails);
        // Refresh Token 생성
        final String refreshToken = generateRefreshToken(claims, customUserDetails);

        // Refresh Token 저장 - REDIS
//        RefreshToken rt = RefreshToken.builder()
//                .refreshToken(refreshToken)
//                .email(user.getEmail())
//                .build();
//        refreshTokenService.saveRefreshToken(rt);

        return JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /*
     *   AccessToken 생성
     */
    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(new HashMap<>(), userDetails, new Date(System.currentTimeMillis() + expirationTime));
    }

    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return generateAccessToken(extraClaims, userDetails, new Date(System.currentTimeMillis() + expirationTime));
    }

    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails, Date expiredTime) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredTime)
                .signWith(getSignInkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /*
     *   RefreshToken 생성
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return generateAccessToken(new HashMap<>(), userDetails, new Date(System.currentTimeMillis() + expirationTime * 7));
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return generateRefreshToken(extraClaims, userDetails, new Date(System.currentTimeMillis() + expirationTime * 7));
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails, Date expiredTime) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredTime)
                .signWith(getSignInkey(), SignatureAlgorithm.HS256)
                .compact();
    }


    /*
     *   Token 검증
     */
    public boolean isTokenValid(String token) {
        Claims claims = extractAllClaims(token);

        try {
            if (!claims.containsKey(ROLE_CLAIM)) return false;
            if (!claims.containsKey(IDENTIFICATION_CLAIM)) return false;
        } catch (RuntimeException e) { // covered for NullPointException, IllegalArgumentException
            throw new JwtException("잘못된 정보입니다");
        }

        String claimsSubject = claims.getSubject();

        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    /*
     *   Token 정보 추출
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInkey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

}
