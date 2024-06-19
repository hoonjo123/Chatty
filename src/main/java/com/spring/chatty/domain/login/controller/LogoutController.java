package com.spring.chatty.domain.login.controller;

import com.spring.chatty.common.jjwt.JwtTokenProvider;
import com.spring.chatty.common.redis.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logout")
public class LogoutController {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @PostMapping
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getUsername(token);
            redisService.deleteValue(email + ":RefreshToken");
            return new ResponseEntity<>("로그아웃이 성공하였습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("유효하지 않은 토큰입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
