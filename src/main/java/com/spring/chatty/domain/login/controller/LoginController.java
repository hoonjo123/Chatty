package com.spring.chatty.domain.login.controller;

import com.spring.chatty.common.response.CommonResponse;
import com.spring.chatty.domain.login.dto.req.LoginReqDto;
import com.spring.chatty.domain.login.dto.res.LoginResDto;
import com.spring.chatty.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;
    private final StringRedisTemplate stringRedisTemplate;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginReqDto dto) {
        LoginResDto loginResDto = loginService.memberLogin(dto);

        return CommonResponse.responseMessage(
                HttpStatus.OK,
                "로그인이 되었습니다",
                loginResDto
        );
    }
}
