package com.spring.chatty.domain.friend.repository;

import com.spring.chatty.domain.friend.domain.Friend;
import com.spring.chatty.domain.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByUser(User user);
    List<Friend> findByFriend(User friend);
    boolean existsByUserAndFriend(User user, User friend);
    Optional<Friend> findByUserAndFriend(User user, User friend);
}
