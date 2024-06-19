package com.spring.chatty.domain.login.service;

import com.spring.chatty.common.jjwt.JwtTokenProvider;
import com.spring.chatty.common.redis.RedisService;
import com.spring.chatty.domain.login.dto.req.LoginReqDto;
import com.spring.chatty.domain.login.dto.res.LoginResDto;
import com.spring.chatty.domain.member.domain.User;
import com.spring.chatty.domain.member.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RedisService redisService;

    @Override
    public LoginResDto memberLogin(LoginReqDto dto) {
        User findUser = userRepository.findByEmail(dto.email())
                .filter(it -> passwordEncoder.matches(dto.password(), it.getPassword()))
                .orElseThrow(() -> new EntityNotFoundException("이메일 혹은 비밀번호를 다시 확인해주세요."));

        String accessToken = jwtTokenProvider.createToken(findUser.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        redisService.setValue(findUser.getEmail() + ":RefreshToken", refreshToken, 1, TimeUnit.DAYS);

        return new LoginResDto(findUser.getId(), findUser.getUsername(), findUser.getEmail(), accessToken, refreshToken);
    }

    @Override
    public String generateVerificationCode(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}