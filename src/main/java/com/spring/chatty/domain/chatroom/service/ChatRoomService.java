package com.spring.chatty.domain.chatroom.service;

import com.spring.chatty.domain.chatroom.domain.ChatRoom;
import com.spring.chatty.domain.chatroom.dto.req.ChatRoomReqDto;
import com.spring.chatty.domain.chatroom.dto.res.ChatRoomResDto;
import com.spring.chatty.domain.chatroom.repository.ChatRoomRepository;
import com.spring.chatty.domain.member.domain.User;
import com.spring.chatty.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatRoomResDto createChatRoom(ChatRoomReqDto chatRoomReqDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(chatRoomReqDto.getName());
        chatRoom.setOwner(user);
        chatRoom = chatRoomRepository.save(chatRoom);
        return new ChatRoomResDto(chatRoom.getId(), chatRoom.getName());
    }
    @Transactional(readOnly = true)
    public List<ChatRoomResDto> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return chatRooms.stream()
                .map(chatRoom -> new ChatRoomResDto(chatRoom.getId(), chatRoom.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ChatRoomResDto updateChatRoomName(Long chatRoomId, ChatRoomReqDto chatRoomReqDto, String email) throws IllegalAccessException {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
        if (!chatRoom.getOwner().getEmail().equals(email)) {
            throw new IllegalAccessException("수정권한이 없습니다 - 채팅방을 만든 사람만 수정할 수 있습니다.");
        }
        chatRoom.setName(chatRoomReqDto.getName());
        chatRoom = chatRoomRepository.save(chatRoom);
        return new ChatRoomResDto(chatRoom.getId(), chatRoom.getName());
    }
    @Transactional
    public boolean deleteChatRoom(Long id, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));

        if (!chatRoom.getOwner().equals(user)) {
            return false;
        }

        chatRoomRepository.delete(chatRoom);
        return true;
    }

}
