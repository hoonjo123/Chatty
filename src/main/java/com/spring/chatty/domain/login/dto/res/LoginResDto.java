package com.spring.chatty.domain.login.dto.res;

import lombok.Builder;

@Builder
public record LoginResDto(
        Long id,
        String username,
        String email,
        String accessToken,
        String refreshToken
) {
}
