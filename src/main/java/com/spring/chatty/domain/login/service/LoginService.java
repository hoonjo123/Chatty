package com.spring.chatty.domain.login.service;

import com.spring.chatty.domain.login.dto.req.LoginReqDto;
import com.spring.chatty.domain.login.dto.res.LoginResDto;

public interface LoginService {
    LoginResDto memberLogin(LoginReqDto dto);
    String generateVerificationCode(int length);
}