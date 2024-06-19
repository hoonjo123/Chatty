package com.spring.chatty.domain.chatroom.controller;

import com.spring.chatty.domain.chatroom.dto.req.ChatRoomReqDto;
import com.spring.chatty.domain.chatroom.dto.res.ChatRoomResDto;
import com.spring.chatty.domain.chatroom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/create")
    public ResponseEntity<ChatRoomResDto> createChatRoom(@RequestBody ChatRoomReqDto chatRoomReqDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            return ResponseEntity.badRequest().body(null);
        }
        ChatRoomResDto chatRoomResDto = chatRoomService.createChatRoom(chatRoomReqDto, userDetails.getUsername());
        return ResponseEntity.ok(chatRoomResDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ChatRoomResDto>> getAllChatRooms() {
        List<ChatRoomResDto> chatRoomList = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRoomList);
    }

    @PatchMapping("/{chatRoomId}/update")
    public ResponseEntity<ChatRoomResDto> updateChatRoomName(
            @PathVariable Long chatRoomId,
            @RequestBody ChatRoomReqDto chatRoomReqDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            ChatRoomResDto chatRoomResDto = chatRoomService.updateChatRoomName(chatRoomId, chatRoomReqDto, userDetails.getUsername());
            return ResponseEntity.ok(chatRoomResDto);
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChatRoom(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            return ResponseEntity.badRequest().body("사용자가 인증되지 않았습니다.");
        }
        boolean isDeleted = chatRoomService.deleteChatRoom(id, userDetails.getUsername());
        if (isDeleted) {
            return ResponseEntity.ok("채팅방이 성공적으로 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이 채팅방을 삭제할 권한이 없습니다.");
        }
    }

}
