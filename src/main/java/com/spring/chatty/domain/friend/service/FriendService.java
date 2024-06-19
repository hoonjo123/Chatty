package com.spring.chatty.domain.friend.service;

import com.spring.chatty.domain.friend.domain.Friend;
import com.spring.chatty.domain.friend.repository.FriendRepository;
import com.spring.chatty.domain.member.domain.User;
import com.spring.chatty.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addFriend(String userEmail, String friendEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        User friend = userRepository.findByEmail(friendEmail)
                .orElseThrow(() -> new IllegalArgumentException("친구를 찾을 수 없습니다."));

        if (friendRepository.existsByUserAndFriend(user, friend)) {
            throw new IllegalArgumentException("이미 친구로 추가되었습니다.");
        }

        Friend friendship = new Friend();
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendRepository.save(friendship);
    }

    @Transactional(readOnly = true)
    public List<Friend> getFriends(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return friendRepository.findByUser(user);
    }
    @Transactional
    public void deleteFriend(String userEmail, String friendEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        User friend = userRepository.findByEmail(friendEmail)
                .orElseThrow(() -> new IllegalArgumentException("친구를 찾을 수 없습니다."));

        Friend friendship = friendRepository.findByUserAndFriend(user, friend)
                .orElseThrow(() -> new IllegalArgumentException("친구 관계가 존재하지 않습니다."));

        friendRepository.delete(friendship);
    }
}
