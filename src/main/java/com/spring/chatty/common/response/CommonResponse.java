package com.spring.chatty.common.response;


import com.spring.chatty.domain.chatroom.dto.res.ChatRoomResDto;
import com.spring.chatty.domain.member.dto.res.UserResDto;
import com.spring.chatty.domain.message.dto.MessageResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponse {
    private HttpStatus httpStatus;
    private String message;

    @Schema(description = "반환 오브젝트", anyOf = {
            UserResDto.class,
            ChatRoomResDto.class,
            MessageResDto.class
    })
    private Object result;

    public static ResponseEntity<CommonResponse> responseMessage(HttpStatus status, String message, Object object) {
        return new ResponseEntity<>(
                CommonResponse.builder()
                        .httpStatus(status)
                        .message(message)
                        .result(object)
                        .build(),
                status
        );
    }

    public static ResponseEntity<CommonResponse> responseMessage(HttpStatus status, String message) {
        return new ResponseEntity<>(
                CommonResponse.builder()
                        .httpStatus(status)
                        .message(message)
                        .build(),
                status
        );
    }
}
