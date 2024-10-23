package com.example.temp.common.filter;

import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.user.domain.value.UserRole;
import com.example.temp.user.service.impl.JwtTokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    /**
     * JWT 토큰 검증 필터 수행
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        // JWT가 헤더에 있는 경우
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {

            String token = authorizationHeader.substring(BEARER_PREFIX.length());

            Claims claims = jwtTokenService.extractAllClaims(token);

            try {
                long userId = Long.parseLong(claims.getSubject());
								String userRole = (String) claims.get("role");

								CustomUserDetails customUserDetails = new CustomUserDetails(userId, UserRole.valueOf(userRole));

								UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
										customUserDetails,
										null,
										List.of(new SimpleGrantedAuthority("ROLE_" + userRole))
								);

							SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        filterChain.doFilter(request, response);
    }
}
