package com.spring.chatty.domain.member.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserCreateReqDto(
        @NotBlank(message = "이름을 입력해주세요.")
        String username,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        String password,

        @NotBlank(message = "이메일을 입력해주세요.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @JsonFormat(pattern = "yyyyMMdd")
        String birthDay,

        @NotBlank(message = "주소를 입력해주세요.")
        String zoneCode,

        @NotBlank(message = "주소를 입력해주세요")
        String roadAddress,

        @NotBlank(message = "주소를 입력해주세요")
        String detailAddress,

        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = "올바른 형식의 핸드폰 번호를 입력해주세요. 01x-xxx(x)-xxxx")
        String phone

        ) {

}
