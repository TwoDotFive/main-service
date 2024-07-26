package com.example.temp.user.service.impl;

import com.example.temp.user.domain.value.UserRole;
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

    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expiration-time}")
    private long expirationTime;

    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String token) {
        Claims claims = extractAllClaims(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    /*
     *   Token에서 사용자 이름 추출
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /*
     *   AccessToken 생성
     */
    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(new HashMap<>(), userDetails, new Date(System.currentTimeMillis() + expirationTime));
    }

    public String generateAccessToken(Map<String, String> extraClaims, UserDetails userDetails) {
        return generateAccessToken(extraClaims, userDetails, new Date(System.currentTimeMillis() + expirationTime));
    }

    public String generateAccessToken(Map<String, String> extraClaims, UserDetails userDetails, Date expiredTime) {
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

    public String generateRefreshToken(Map<String, String> extraClaims, UserDetails userDetails) {
        return generateRefreshToken(extraClaims, userDetails, new Date(System.currentTimeMillis() + expirationTime * 7));
    }

    public String generateRefreshToken(Map<String, String> extraClaims, UserDetails userDetails, Date expiredTime) {
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
            if (!claims.containsKey("role")) {
                UserRole.valueOf(claims.get("role", String.class));
                return false;
            }
            if (!claims.containsKey("name")) return false;
            if (!claims.containsKey("profileImageUrl")) return false;
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

    public String generateExpiredAccessToken(Map<String, String> claims, UserDetails user) {
        Date now = new Date();

        // 만료기간을 현재 시각보다 이전으로
        Date expiryTime = new Date(now.getTime() - 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryTime)
                .signWith(getSignInkey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
