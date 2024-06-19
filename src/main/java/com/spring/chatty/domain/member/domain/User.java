package com.spring.chatty.domain.member.domain;


import com.spring.chatty.common.domain.BaseEntity;
import com.spring.chatty.domain.chatroom.domain.ChatRoom;
import com.spring.chatty.domain.message.domain.Message;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<ChatRoom> chatRooms;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;
}
