package com.spring.chatty.domain.member.controller;

import com.spring.chatty.domain.member.dto.req.UserCreateReqDto;
import com.spring.chatty.domain.member.dto.res.UserResDto;
import com.spring.chatty.domain.member.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResDto> createUser(@RequestBody @Valid UserCreateReqDto dto) {
        UserResDto userResDto = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResDto);
    }
}
