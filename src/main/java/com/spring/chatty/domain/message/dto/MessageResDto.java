package com.spring.chatty.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResDto {
    private Long id;
    private Long chatRoomId;
    private Long userId;
    private String content;
}
