package com.spring.chatty.domain.chatroom.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.spring.chatty.domain.member.domain.User;  // 올바른 패키지 경로로 수정
import com.spring.chatty.domain.message.domain.Message;  // 올바른 패키지 경로로 수정

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "chatRoom")
    private List<Message> messages;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
