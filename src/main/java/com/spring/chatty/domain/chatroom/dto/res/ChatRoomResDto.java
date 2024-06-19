package com.spring.chatty.domain.chatroom.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomResDto {
    private Long id;
    private String name;
}
