package com.spring.chatty.common.domain;

import com.spring.chatty.domain.member.domain.User;
import com.spring.chatty.domain.member.dto.req.UserCreateReqDto;
import com.spring.chatty.domain.member.dto.res.UserResDto;
import org.springframework.stereotype.Component;

@Component
public class TypeChange {
    public User userCreateReqDtoToUser(UserCreateReqDto dto, String encodedPassword) {
        return User.builder()
                .username(dto.username())
                .password(encodedPassword)
                .email(dto.email())
                .build();
    }

    public UserResDto userToUserResDto(User user) {
        return new UserResDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}