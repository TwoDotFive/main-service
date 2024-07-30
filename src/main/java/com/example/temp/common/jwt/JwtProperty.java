package com.example.temp.common.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
class JwtProperty {

	private String secretKey;
	private long accessTokenExpirationTime;
	private long refreshTokenExpirationTime;

}
