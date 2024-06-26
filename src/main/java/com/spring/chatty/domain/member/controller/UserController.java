package com.spring.chatty.domain.member.controller;

import com.spring.chatty.common.response.CommonResponse;
import com.spring.chatty.domain.member.dto.req.UserCreateReqDto;
import com.spring.chatty.domain.member.dto.res.UserResDto;
import com.spring.chatty.domain.member.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid UserCreateReqDto dto) {
        UserResDto userResDto = userService.create(dto);
        return CommonResponse.responseMessage(HttpStatus.CREATED, "사용자가 성공적으로 생성되었습니다.", userResDto);
    }

    @GetMapping("/list")
    public ResponseEntity<CommonResponse> getAllUsers() {
        List<UserResDto> users = userService.getAllUsers();
        return CommonResponse.responseMessage(HttpStatus.OK, "사용자 목록 조회 성공", users);
    }
}
