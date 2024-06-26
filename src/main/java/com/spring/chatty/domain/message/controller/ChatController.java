package com.spring.chatty.domain.message.controller;

import com.spring.chatty.domain.message.domain.Message;
import com.spring.chatty.domain.message.dto.ChatMessage;
import com.spring.chatty.domain.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessageRepository messageRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // 메시지를 데이터베이스에 저장
        Message message = new Message();
        message.setContent(chatMessage.getContent());
        message.setSentAt(LocalDateTime.now());
        message.setUsername(username);
        messageRepository.save(message);

        chatMessage.setSender(username);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage) {
        chatMessage.setContent("New user added: " + chatMessage.getSender());
        return chatMessage;
    }
}
