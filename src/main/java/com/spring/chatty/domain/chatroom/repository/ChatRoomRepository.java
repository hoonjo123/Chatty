package com.spring.chatty.domain.chatroom.repository;

import com.spring.chatty.domain.chatroom.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
