package com.example.temp.common.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.temp.common.util.IdUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static SecretKey secretKey;
	private static JwtProperty jwtProperty;

	@Autowired
	private JwtUtil(JwtProperty jwtProperty) {
		JwtUtil.jwtProperty = jwtProperty;
		JwtUtil.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperty.getSecretKey()));
	}

	// 새로운 액세스 토큰 및 리프레시 토큰 생성
	public static JwtResponse createTokens(long userId) {
		return new JwtResponse(generateAccessToken(userId), generateRefreshToken(userId));
	}

	// 리프레시 토큰을 통한 액세스 토큰 재발급
	public static JwtResponse refreshAccessToken(String refreshToken) {
		Claims claims = getClaims(refreshToken);
		Long userId = IdUtil.toLong(claims.get("uid", String.class));
		return new JwtResponse(generateAccessToken(userId), null);
	}

	// 액세스 토큰 생성
	private static String generateAccessToken(Long userId) {
		ClaimsBuilder claimsBuilder = Jwts.claims();
		claimsBuilder.subject(IdUtil.toString(userId));

		return Jwts.builder()
			.claims(claimsBuilder.build())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + jwtProperty.getAccessTokenExpirationTime()))
			.signWith(secretKey, SIG.HS256)
			.compact();
	}

	// 리프레시 토큰 생성
	private static String generateRefreshToken(long userId) {
		ClaimsBuilder claimsBuilder = Jwts.claims();
		claimsBuilder.add("uid", IdUtil.toString(userId));

		return Jwts.builder()
			.claims(claimsBuilder.build())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + jwtProperty.getAccessTokenExpirationTime()))
			.signWith(secretKey, SIG.HS256)
			.compact();
	}

	private static Claims getClaims(String token) {
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	public static long getUserId(String token) {
		return IdUtil.toLong(getClaims(token).getSubject());
	}

	// 토큰 유효성 검증 메서드
	public static boolean validate(String token) {
		try {
			Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}

