package com.spring.chatty.domain.member.service;

import com.spring.chatty.common.domain.TypeChange;
import com.spring.chatty.domain.member.domain.User;
import com.spring.chatty.domain.member.dto.req.UserCreateReqDto;
import com.spring.chatty.domain.member.dto.res.UserResDto;
import com.spring.chatty.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TypeChange typeChange;

    @Transactional
    public UserResDto create(UserCreateReqDto dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(dto.password());
        User user = typeChange.userCreateReqDtoToUser(dto, encodedPassword);
        User savedUser = userRepository.save(user);
        return typeChange.userToUserResDto(savedUser);
    }
}
