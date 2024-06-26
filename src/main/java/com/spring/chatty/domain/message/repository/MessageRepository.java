package com.spring.chatty.domain.message.repository;

import com.spring.chatty.domain.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoomId(Long chatRoomId);
}
